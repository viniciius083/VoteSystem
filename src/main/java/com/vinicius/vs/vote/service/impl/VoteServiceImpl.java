package com.vinicius.vs.vote.service.impl;

import com.vinicius.vs.associate.model.Associate;
import com.vinicius.vs.associate.service.IAssociateService;
import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.agenda.model.Agenda;
import com.vinicius.vs.agenda.service.IAgendaService;
import com.vinicius.vs.vote.dtos.VoteConfirmationDTO;
import com.vinicius.vs.vote.dtos.VoteDTO;
import com.vinicius.vs.vote.model.Vote;
import com.vinicius.vs.vote.repository.IVoteRepository;
import com.vinicius.vs.vote.service.IVoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements IVoteService {

    private final IVoteRepository votoRepository;

    private final IAgendaService pautaService;

    private final IAssociateService associadoService;

    /**
     * Método para realizar votação em uma pauta, é necessário abrir a pauta antes da votação.
     * @param voteDTO objeto de votação
     * @return confirmação do voto
     */
    @Override
    public VoteConfirmationDTO vote(VoteDTO voteDTO) {
        Agenda pauta = pautaService.listAgenda(voteDTO.getAgendaId());
        Associate associado = associadoService.listAssociates(voteDTO.getAssociateId());

        if(pauta.getOpenVote() == null){
            throw new DataIntegratyViolationException("A pauta não foi aberta!");
        }
        if(LocalDateTime.now().isAfter(pauta.getCloseVote())){
            throw new DataIntegratyViolationException("A pauta já foi encerrada!");
        }

       if(pauta.getVotes().stream().anyMatch(voto -> voto.getAssociate().getId().equals(associado.getId()))){
           throw new DataIntegratyViolationException("O associado já votou na pauta.");
        };

       pauta.setQuantityVotes(pauta.getQuantityVotes() +1);
       Vote vote = new Vote(pauta, associado, voteDTO.getVote());
       votoRepository.save(vote);

       return new VoteConfirmationDTO(associado.getName(), vote.getVote(), pauta.getSubject());
    }
}
