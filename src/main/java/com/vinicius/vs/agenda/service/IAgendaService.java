package com.vinicius.vs.agenda.service;

import com.vinicius.vs.agenda.dtos.OpenVoteDTO;
import com.vinicius.vs.agenda.dtos.CreateAgendaDTO;
import com.vinicius.vs.agenda.dtos.AgendaDTO;
import com.vinicius.vs.agenda.model.Agenda;

import java.util.List;

public interface IAgendaService {
    AgendaDTO createAgenda(CreateAgendaDTO createAgendaDTO);

    AgendaDTO openVote(OpenVoteDTO openVoteDTO);

    List<AgendaDTO> listAgendas();

    Agenda listAgenda(long id);

}
