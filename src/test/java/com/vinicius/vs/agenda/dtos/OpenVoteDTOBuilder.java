package com.vinicius.vs.agenda.dtos;

public final class OpenVoteDTOBuilder {
    private OpenVoteDTO openVoteDTO;

    private OpenVoteDTOBuilder() {
        openVoteDTO = new OpenVoteDTO();
    }

    public static OpenVoteDTOBuilder anOpenVoteDTO() {
        OpenVoteDTOBuilder builder = new OpenVoteDTOBuilder();
        builder.openVoteDTO = new OpenVoteDTO();
        builder.openVoteDTO.setAgendaId(1L);
        builder.openVoteDTO.setTime(1);
        return builder;
    }

    public OpenVoteDTOBuilder withAgendaId(long agendaId) {
        openVoteDTO.setAgendaId(agendaId);
        return this;
    }

    public OpenVoteDTOBuilder withTime(int time) {
        openVoteDTO.setTime(time);
        return this;
    }

    public OpenVoteDTO build() {
        return openVoteDTO;
    }
}
