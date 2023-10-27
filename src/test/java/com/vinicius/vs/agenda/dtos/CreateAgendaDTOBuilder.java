package com.vinicius.vs.agenda.dtos;public final class CreateAgendaDTOBuilder {
    private CreateAgendaDTO createAgendaDTO;

    private CreateAgendaDTOBuilder() {
        createAgendaDTO = new CreateAgendaDTO();
    }

    public static CreateAgendaDTOBuilder aCreateAgendaDTO() {
        CreateAgendaDTOBuilder builder = new CreateAgendaDTOBuilder();
        builder.createAgendaDTO = new CreateAgendaDTO();
        builder.createAgendaDTO.setSubject("Compra de Curso");
        return builder;
    }

    public CreateAgendaDTOBuilder withSubject(String subject) {
        createAgendaDTO.setSubject(subject);
        return this;
    }

    public CreateAgendaDTO build() {
        return createAgendaDTO;
    }
}
