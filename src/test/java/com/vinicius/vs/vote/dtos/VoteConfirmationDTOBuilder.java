package com.vinicius.vs.vote.dtos;

import com.vinicius.vs.agenda.enumeration.AgendaResult;

public final class VoteConfirmationDTOBuilder {
    private VoteConfirmationDTO voteConfirmationDTO;

    private VoteConfirmationDTOBuilder() {
        voteConfirmationDTO = new VoteConfirmationDTO();
    }

    public static VoteConfirmationDTOBuilder aVoteConfirmationDTO() {
        VoteConfirmationDTOBuilder builder = new VoteConfirmationDTOBuilder();
        builder.voteConfirmationDTO = new VoteConfirmationDTO();
        builder.voteConfirmationDTO.setName("Vinicius");
        builder.voteConfirmationDTO.setVote(AgendaResult.SIM);
        builder.voteConfirmationDTO.setSubject("Compra de Curso");
        return builder;
    }

    public VoteConfirmationDTOBuilder withName(String name) {
        voteConfirmationDTO.setName(name);
        return this;
    }

    public VoteConfirmationDTOBuilder withVote(AgendaResult vote) {
        voteConfirmationDTO.setVote(vote);
        return this;
    }

    public VoteConfirmationDTOBuilder withSubject(String subject) {
        voteConfirmationDTO.setSubject(subject);
        return this;
    }

    public VoteConfirmationDTO build() {
        return voteConfirmationDTO;
    }
}
