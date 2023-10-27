package com.vinicius.vs.vote.dtos;

import com.vinicius.vs.agenda.enumeration.AgendaResult;

public final class VoteDTOBuilder {
    private VoteDTO voteDTO;

    private VoteDTOBuilder() {
        voteDTO = new VoteDTO();
    }

    public static VoteDTOBuilder aVoteDTO() {
        VoteDTOBuilder builder = new VoteDTOBuilder();
        builder.voteDTO = new VoteDTO();
        builder.voteDTO.setAssociateId(1L);
        builder.voteDTO.setVote(AgendaResult.SIM);
        builder.voteDTO.setAgendaId(1L);
        return builder;
    }

    public VoteDTOBuilder withAssociateId(long associateId) {
        voteDTO.setAssociateId(associateId);
        return this;
    }

    public VoteDTOBuilder withVote(AgendaResult vote) {
        voteDTO.setVote(vote);
        return this;
    }

    public VoteDTOBuilder withAgendaId(long agendaId) {
        voteDTO.setAgendaId(agendaId);
        return this;
    }

    public VoteDTO build() {
        return voteDTO;
    }
}
