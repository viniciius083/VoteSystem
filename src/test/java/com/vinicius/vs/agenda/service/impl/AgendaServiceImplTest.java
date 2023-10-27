package com.vinicius.vs.agenda.service.impl;

import com.vinicius.vs.agenda.dtos.AgendaDTO;
import com.vinicius.vs.agenda.dtos.CreateAgendaDTO;
import com.vinicius.vs.agenda.dtos.OpenVoteDTO;
import com.vinicius.vs.agenda.dtos.OpenVoteDTOBuilder;
import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.agenda.model.Agenda;
import com.vinicius.vs.agenda.model.AgendaBuilder;
import com.vinicius.vs.agenda.repository.IAgendaRepository;
import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.exceptions.errors.ObjectNotFoundException;
import com.vinicius.vs.rabbitmq.service.RabbitmqService;
import com.vinicius.vs.vote.model.VoteBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static com.vinicius.vs.agenda.model.AgendaBuilder.anAgenda;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendaServiceImplTest {

    @InjectMocks
    private AgendaServiceImpl agendaService;

    @Mock
    private IAgendaRepository agendaRepository;

    @Mock
    private RabbitmqService rabbitmqService;

    @Test
    void createAgenda() {
        Agenda agenda = anAgenda().build();
        when(agendaRepository.save(ArgumentMatchers.any())).thenReturn(agenda);

        AgendaDTO agendaDTO = agendaService.createAgenda(new CreateAgendaDTO());


        Assertions.assertNotNull(agendaDTO);
        assertEquals(agenda.getSubject(), agendaDTO.getSubject());
        assertEquals(agenda.getId(), agendaDTO.getId());
    }

    @Test
    void openVote() {
        OpenVoteDTO openVoteDTO = OpenVoteDTOBuilder.anOpenVoteDTO().build();
        Agenda agenda = AgendaBuilder.anAgenda().build();

        when(agendaRepository.findById(anyLong())).thenReturn(of(agenda));
        when(agendaRepository.save(ArgumentMatchers.any(Agenda.class))).thenReturn(agenda);
        //fazer esse teste de thread

        AgendaDTO agendaDTO = agendaService.openVote(openVoteDTO);

        assertEquals(agenda.getId(), agendaDTO.getId());
        assertTrue(agendaDTO.getOpenVote().toLocalTime().until(LocalDateTime.now().toLocalTime(), ChronoUnit.SECONDS) < 15);
        assertNotNull(agendaDTO.getCloseVote());
    }

    @Test()
    public void openVoteButAgendaOpen() {
        OpenVoteDTO openVoteDTO = OpenVoteDTOBuilder.anOpenVoteDTO().build();
        Agenda agenda = AgendaBuilder.anAgenda().withOpenVote(LocalDateTime.now().minusMinutes(5)).build();
        when(agendaRepository.findById(anyLong())).thenReturn(of(agenda));

        DataIntegratyViolationException ex = Assertions.assertThrows(DataIntegratyViolationException.class, () -> agendaService.openVote(openVoteDTO));

        assertTrue(ex.getMessage().contains("A Pauta já foi aberta!"));
        verify(agendaRepository, never()).save(ArgumentMatchers.any());
    }

    @Test
    public void openVoteButAgendaNonExisting(){
        OpenVoteDTO openVoteDTO = OpenVoteDTOBuilder.anOpenVoteDTO().build();

        when(agendaRepository.findById(anyLong())).thenReturn(empty());

        ObjectNotFoundException ex = Assertions.assertThrows(ObjectNotFoundException.class, () -> agendaService.openVote(openVoteDTO));
        assertTrue(ex.getMessage().contains("Pauta não encontrada!"));
    }

    @Test
    public void listAgendas() {
        Agenda agenda = anAgenda().build();
        when(agendaRepository.findAll()).thenReturn(Collections.singletonList(agenda));
        List<AgendaDTO> result = agendaService.listAgendas();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), agenda.getId());
        assertEquals(result.get(0).getSubject(), agenda.getSubject());
    }


    @Test
    public void listAgendaForExistingId() {
        Agenda agenda = anAgenda().build();

        when(agendaRepository.findById(agenda.getId())).thenReturn(of(agenda));
        Agenda result = agendaService.listAgenda(agenda.getId());

        assertEquals(agenda, result);
    }

    @Test
    public void listAgendaForNonExistingId() {
        Agenda agenda = anAgenda().build();

        when(agendaRepository.findById(agenda.getId())).thenReturn(empty());


        ObjectNotFoundException ex = Assertions.assertThrows(ObjectNotFoundException.class, () -> agendaService.listAgenda(agenda.getId()));
        assertTrue(ex.getMessage().contains("Pauta não encontrada!"));
        verify(agendaRepository, times(1)).findById(agenda.getId());
    }

    @Test
    void countVotesSuccessful() {
        Agenda agenda = AgendaBuilder.anAgenda().withQuantityVotes(1).withResult(AgendaResult.SIM).build();
        agenda.setVotes(Collections.singletonList(VoteBuilder.aVote().build()));

        when(agendaRepository.findById(anyLong())).thenReturn(of(agenda));
        agendaService.countVotes(agenda.getId());

        assertEquals(agenda.getResult(), AgendaResult.SIM);
        assertEquals(agenda.getQuantityVotes(), 1);
        assertEquals(agenda.getVotes().size(), 1);

    }

    @Test
    void countVotes() {
        Agenda agenda = AgendaBuilder.anAgenda().withResult(AgendaResult.SIM).build();
        agenda.setVotes(Collections.singletonList(VoteBuilder.aVote().build()));


        when(agendaRepository.findById(anyLong())).thenReturn(empty());
        agendaService.countVotes(agenda.getId());

        verify(agendaRepository, never()).save(any());
    }

}