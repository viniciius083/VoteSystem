package com.vinicius.vs.voto.service.impl;

import com.vinicius.vs.associado.model.Associado;
import com.vinicius.vs.associado.service.IAssociadoService;
import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.pauta.model.Pauta;
import com.vinicius.vs.pauta.service.IPautaService;
import com.vinicius.vs.voto.dtos.ConfirmacaoVoto;
import com.vinicius.vs.voto.dtos.VotarDTO;
import com.vinicius.vs.voto.model.Voto;
import com.vinicius.vs.voto.repository.IVotoRepository;
import com.vinicius.vs.voto.service.IVotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VotoServiceImpl implements IVotoService {

    private final IVotoRepository votoRepository;

    private final IPautaService pautaService;

    private final IAssociadoService associadoService;

    @Override
    public ConfirmacaoVoto votar(VotarDTO votarDTO) {
        Pauta pauta = pautaService.listarPauta(votarDTO.getMeetingId());
        Associado associado = associadoService.listarAssociado(votarDTO.getAssociateId());

        if(pauta.getOpenVote() == null){
            throw new DataIntegratyViolationException("A pauta não foi aberta!");
        }
        if(LocalDateTime.now().isAfter(pauta.getCloseVote())){
            throw new DataIntegratyViolationException("A pauta já foi encerrada!");
        }

       if(pauta.getVotos().stream().anyMatch(voto -> voto.getAssociado().getId().equals(associado.getId()))){
           throw new DataIntegratyViolationException("O associado já votou na pauta.");
        };

       pauta.setQuantityVotes(pauta.getQuantityVotes() +1);

       Voto voto = new Voto(pauta, associado, votarDTO.getVote());

       votoRepository.save(voto);

       return new ConfirmacaoVoto(associado.getName(), voto.getVote(), pauta.getSubject());

        // Criar o voto e adicionar uma schedule de tempo após o tempo definido.
        // Criar os testes
        // Criar integração com validação de CPF

    }
}
