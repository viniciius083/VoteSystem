package com.vinicius.vs.agenda.model;

import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.vote.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public final class AgendaBuilder {
    private Agenda agenda;

    private AgendaBuilder() {
        agenda = new Agenda();
    }

    public static AgendaBuilder anAgenda() {
        AgendaBuilder builder = new AgendaBuilder();
        builder.agenda = new Agenda();
        builder.agenda.setId(1L);
        builder.agenda.setSubject("Compra de Curso");
        return builder;
    }

    public AgendaBuilder withId(Long id) {
        agenda.setId(id);
        return this;
    }

    public AgendaBuilder withSubject(String subject) {
        agenda.setSubject(subject);
        return this;
    }

    public AgendaBuilder withResult(AgendaResult result) {
        agenda.setResult(result);
        return this;
    }

    public AgendaBuilder withOpenVote(LocalDateTime openVote) {
        agenda.setOpenVote(openVote);
        return this;
    }

    public AgendaBuilder withCloseVote(LocalDateTime closeVote) {
        agenda.setCloseVote(closeVote);
        return this;
    }

    public AgendaBuilder withVotes(List<Vote> votes) {
        agenda.setVotes(votes);
        return this;
    }

    public AgendaBuilder withQuantityVotes(int quantityVotes) {
        agenda.setQuantityVotes(quantityVotes);
        return this;
    }

    public Agenda build() {
        return agenda;
    }
}
