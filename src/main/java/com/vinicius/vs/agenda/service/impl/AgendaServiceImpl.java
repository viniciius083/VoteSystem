package com.vinicius.vs.agenda.service.impl;

import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.exceptions.errors.ObjectNotFoundException;
import com.vinicius.vs.agenda.dtos.OpenVoteDTO;
import com.vinicius.vs.agenda.dtos.CreateAgendaDTO;
import com.vinicius.vs.agenda.dtos.AgendaDTO;
import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.agenda.model.Agenda;
import com.vinicius.vs.agenda.repository.IAgendaRepository;
import com.vinicius.vs.agenda.service.IAgendaService;
import com.vinicius.vs.rabbitmq.service.RabbitmqService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AgendaServiceImpl implements IAgendaService {

    private final IAgendaRepository agendaRepository;
    private final RabbitmqService rabbitmqService;

    ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(3);

    public AgendaServiceImpl(IAgendaRepository AgendaRepository, RabbitmqService rabbitmqService) {
        this.agendaRepository = AgendaRepository;
        this.rabbitmqService = rabbitmqService;
    }

    @Override
    public AgendaDTO createAgenda(CreateAgendaDTO createAgendaDTO) {
        log.info("Solicitada a criação de uma pauta.");
        return new AgendaDTO(agendaRepository.save(new Agenda(createAgendaDTO)));
    }

    @Override
    public AgendaDTO openVote(OpenVoteDTO openVoteDTO) {
        log.info("Pauta "+openVoteDTO.getAgendaId()+" está sendo aberta por "+openVoteDTO.getTime()+" minutos.");
        Agenda pauta = agendaRepository.findById(openVoteDTO.getAgendaId()).orElseThrow(() -> new ObjectNotFoundException("Pauta não encontrada!"));

        if(pauta.getOpenVote() != null){
            throw new DataIntegratyViolationException("A Pauta já foi aberta!");
        }

        pauta.setOpenVote(LocalDateTime.now());
        pauta.setCloseVote(LocalDateTime.now().plusMinutes(openVoteDTO.getTime()));
        agendaRepository.save(pauta);

        scheduledThreadPool.schedule(() -> countVotes(openVoteDTO.getAgendaId()),
                (openVoteDTO.getTime() + 10) , TimeUnit.SECONDS);

        return new AgendaDTO(pauta);
    }

    @Override
    public List<AgendaDTO> listAgendas() {
        return agendaRepository.findAll().stream().map(AgendaDTO::new).collect(Collectors.toList());
    }

    @Override
    public Agenda listAgenda(long id) {
        log.info("Buscando pauta de id "+id);
        return agendaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pauta não encontrada!"));
    }

    public void countVotes(long pautaId){
        log.info("Iniciando a contagem dos votos da pauta: "+ pautaId);
        Agenda pauta = agendaRepository.findById(pautaId).orElse(null);

        if (pauta != null) {
            long votesYes = pauta.getVotes().stream().filter(vote -> vote.getVote().equals(AgendaResult.SIM)).count();
            long votesNo = pauta.getVotes().stream().filter(vote -> vote.getVote().equals(AgendaResult.NAO)).count();

            pauta.setResult(votesYes > votesNo ? AgendaResult.SIM : AgendaResult.NAO);
            agendaRepository.save(pauta);

            rabbitmqService.sendMessageAgenda("A pauta: "+pauta.getSubject()+" foi finalizada e foi "+ (pauta.getResult().equals(AgendaResult.SIM) ? "Aprovada":"Negada"));
        }else{
            rabbitmqService.sendMessageAgenda("Não foi encontrada nenhuma pauta referente ao ID.");
        }
    }

}
