package com.vinicius.vs.vote.model;

import com.vinicius.vs.agenda.enumeration.AgendaResult;
import com.vinicius.vs.agenda.model.Agenda;
import com.vinicius.vs.agenda.model.AgendaBuilder;
import com.vinicius.vs.associate.model.Associate;
import com.vinicius.vs.associate.model.AssociateBuilder;

public final class VoteBuilder {
    private Vote vote;

    private VoteBuilder() {
        vote = new Vote();
    }

    public static VoteBuilder aVote() {
        VoteBuilder builder = new VoteBuilder();
        builder.vote = new Vote();
        builder.vote.setId(1L);
        builder.vote.setVote(AgendaResult.SIM);
        builder.vote.setAgenda(AgendaBuilder.anAgenda().build());
        builder.vote.setAssociate(AssociateBuilder.anAssociate().build());
        return builder;
    }

    public VoteBuilder withId(Long id) {
        vote.setId(id);
        return this;
    }

    public VoteBuilder withVote(AgendaResult result) {
        vote.setVote(result);
        return this;
    }

    public VoteBuilder withAgenda(Agenda agenda) {
        vote.setAgenda(agenda);
        return this;
    }

    public VoteBuilder withAssociate(Associate associate) {
        vote.setAssociate(associate);
        return this;
    }

    public Vote build() {
        return vote;
    }
}
