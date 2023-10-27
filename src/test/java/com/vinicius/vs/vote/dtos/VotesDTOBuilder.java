package com.vinicius.vs.vote.dtos;

import com.vinicius.vs.agenda.enumeration.AgendaResult;

public final class VotesDTOBuilder {
    private VotesDTO votesDTO;

    private VotesDTOBuilder() {
        votesDTO = new VotesDTO();
    }

    public static VotesDTOBuilder aVotesDTO() {
        VotesDTOBuilder builder = new VotesDTOBuilder();
        builder.votesDTO = new VotesDTO();
        builder.votesDTO.setVote(AgendaResult.SIM);
        builder.votesDTO.setName("Vinicius");
        return builder;
    }

    public VotesDTOBuilder withVote(AgendaResult vote) {
        votesDTO.setVote(vote);
        return this;
    }

    public VotesDTOBuilder withName(String name) {
        votesDTO.setName(name);
        return this;
    }

    public VotesDTO build() {
        return votesDTO;
    }
}
