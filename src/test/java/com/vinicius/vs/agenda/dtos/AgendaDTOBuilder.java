package com.vinicius.vs.agenda.dtos;

import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.associate.dtos.AssociateDTO;
import com.vinicius.vs.associate.dtos.AssociateDTOBuilder;
import com.vinicius.vs.vote.dtos.VotesDTO;

import java.time.LocalDateTime;
import java.util.List;

public final class AgendaDTOBuilder {
    private AgendaDTO agendaDTO;

    private AgendaDTOBuilder() {
        agendaDTO = new AgendaDTO();
    }

    public static AgendaDTOBuilder anAgendaDTO() {
        AgendaDTOBuilder builder = new AgendaDTOBuilder();
        builder.agendaDTO = new AgendaDTO();
        builder.agendaDTO.setId(1L);
        builder.agendaDTO.setSubject("Compra de Curso");

        return builder;
    }

    public AgendaDTOBuilder withId(long id) {
        agendaDTO.setId(id);
        return this;
    }

    public AgendaDTOBuilder withSubject(String subject) {
        agendaDTO.setSubject(subject);
        return this;
    }

    public AgendaDTOBuilder withResult(AgendaResult result) {
        agendaDTO.setResult(result);
        return this;
    }

    public AgendaDTOBuilder withQuantityVotes(int quantityVotes) {
        agendaDTO.setQuantityVotes(quantityVotes);
        return this;
    }

    public AgendaDTOBuilder withOpenVote(LocalDateTime openVote) {
        agendaDTO.setOpenVote(openVote);
        return this;
    }

    public AgendaDTOBuilder withCloseVote(LocalDateTime closeVote) {
        agendaDTO.setCloseVote(closeVote);
        return this;
    }

    public AgendaDTOBuilder withVotes(List<VotesDTO> votes) {
        agendaDTO.setVotes(votes);
        return this;
    }

    public AgendaDTO build() {
        return agendaDTO;
    }
}
