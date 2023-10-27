package com.vinicius.vs.vote.service.impl;

import com.vinicius.vs.agenda.model.Agenda;
import com.vinicius.vs.agenda.service.IAgendaService;
import com.vinicius.vs.associate.model.Associate;
import com.vinicius.vs.associate.service.IAssociateService;
import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.vote.dtos.VoteConfirmationDTO;
import com.vinicius.vs.vote.dtos.VoteDTO;
import com.vinicius.vs.vote.dtos.VoteDTOBuilder;
import com.vinicius.vs.vote.model.VoteBuilder;
import com.vinicius.vs.vote.repository.IVoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.vinicius.vs.agenda.model.AgendaBuilder.anAgenda;
import static com.vinicius.vs.associate.model.AssociateBuilder.anAssociate;
import static com.vinicius.vs.vote.dtos.VoteDTOBuilder.aVoteDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private IVoteRepository voteRepository;

    @Mock
    private IAgendaService agendaService;

    @Mock
    private IAssociateService associateService;

    @Test
    @DisplayName("Tentativa de voto com agenda sem horario de abertura/fechamento")
    void voteAgendaNotOpen() {
        when(agendaService.listAgenda(anyLong())).thenReturn(anAgenda().build());

        DataIntegratyViolationException ex = Assertions.assertThrows(DataIntegratyViolationException.class, () -> voteService.vote(aVoteDTO().build()));

        assertTrue(ex.getMessage().contains("A pauta não foi aberta!"));
        verify(voteRepository, never()).save(any());
    }

    @Test
    @DisplayName("Tentativa de voto com agenda fechada")
    void voteAgendaClosed() {
        when(agendaService.listAgenda(anyLong())).thenReturn(anAgenda()
                .withOpenVote(LocalDateTime.now().minusMinutes(10))
                .withCloseVote(LocalDateTime.now().minusMinutes(5)).build());

        DataIntegratyViolationException ex = Assertions.assertThrows(DataIntegratyViolationException.class, () -> voteService.vote(aVoteDTO().build()));

        assertTrue(ex.getMessage().contains("A pauta já foi encerrada!"));
        verify(voteRepository, never()).save(any());
    }

    @Test
    @DisplayName("Tentativa de votar duas vezes com o mesmo associado")
    void voteTwoTimesWithOneAssociate() {
        when(associateService.listAssociate(anyLong())).thenReturn(anAssociate().build());
        when(agendaService.listAgenda(anyLong()))
                .thenReturn(anAgenda()
                        .withOpenVote(LocalDateTime.now().minusMinutes(5))
                        .withCloseVote(LocalDateTime.now().plusMinutes(5))
                        .withQuantityVotes(1)
                        .withVotes(Collections.singletonList(VoteBuilder.aVote().build()))
                        .build());

        DataIntegratyViolationException ex = Assertions.assertThrows(DataIntegratyViolationException.class, () -> voteService.vote(aVoteDTO().build()));

        assertTrue(ex.getMessage().contains("O associado já votou na pauta."));
        verify(voteRepository, never()).save(any());
    }

    @Test
    @DisplayName("Tentativa de votar duas vezes com o mesmo associado")
    void voteSuccessful() {
        Associate associate = anAssociate().build();
        Agenda agenda = anAgenda()
                .withOpenVote(LocalDateTime.now().minusMinutes(5))
                .withCloseVote(LocalDateTime.now().plusMinutes(5))
                .build();

        when(associateService.listAssociate(anyLong())).thenReturn(associate);
        when(agendaService.listAgenda(anyLong()))
                .thenReturn(agenda);

        VoteDTO voteDTO = VoteDTOBuilder.aVoteDTO().build();
        VoteConfirmationDTO voteConfirmationDTO = voteService.vote(voteDTO);


        verify(voteRepository, times(1)).save(any());
        verify(agendaService, times(1)).listAgenda(anyLong());
        verify(associateService, times(1)).listAssociate(anyLong());

        assertEquals(voteConfirmationDTO.getVote(), voteDTO.getVote());
        assertEquals(voteConfirmationDTO.getSubject(), agenda.getSubject());
        assertEquals(voteConfirmationDTO.getName(), associate.getName());

    }

}