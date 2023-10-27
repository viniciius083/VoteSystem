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

    private final IAgendaService agendaService;

    private final IAssociateService associateService;

    /**
     * Método para realizar votação em uma pauta, é necessário abrir a pauta antes da votação.
     * @param voteDTO objeto de votação
     * @return confirmação do voto
     */
    @Override
    public VoteConfirmationDTO vote(VoteDTO voteDTO) {
        Agenda agenda = agendaService.listAgenda(voteDTO.getAgendaId());
        Associate associate = associateService.listAssociate(voteDTO.getAssociateId());

        if(agenda.getOpenVote() == null){
            throw new DataIntegratyViolationException("A pauta não foi aberta!");
        }
        if(LocalDateTime.now().isAfter(agenda.getCloseVote())){
            throw new DataIntegratyViolationException("A pauta já foi encerrada!");
        }

       if(agenda.getVotes().stream().anyMatch(voto -> voto.getAssociate().getId().equals(associate.getId()))){
           throw new DataIntegratyViolationException("O associado já votou na pauta.");
        };

       agenda.setQuantityVotes(agenda.getQuantityVotes() +1);
       Vote vote = new Vote(agenda, associate, voteDTO.getVote());
       votoRepository.save(vote);

       return new VoteConfirmationDTO(associate.getName(), vote.getVote(), agenda.getSubject());
    }
}
