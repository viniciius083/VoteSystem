package com.vinicius.vs.associate.dtos;

import java.time.LocalDate;

public final class AssociateDTOBuilder {
    private AssociateDTO associateDTO;

    private AssociateDTOBuilder() {
        associateDTO = new AssociateDTO();
    }

    public static AssociateDTOBuilder anAssociateDTO() {
        AssociateDTOBuilder builder = new AssociateDTOBuilder();
        builder.associateDTO = new AssociateDTO();
        builder.associateDTO.setId(1L);
        builder.associateDTO.setCpf("12291783416");
        builder.associateDTO.setName("Vinicius");
        return builder;
    }

    public AssociateDTOBuilder withId(Long id) {
        associateDTO.setId(id);
        return this;
    }

    public AssociateDTOBuilder withCpf(String cpf) {
        associateDTO.setCpf(cpf);
        return this;
    }

    public AssociateDTOBuilder withName(String name) {
        associateDTO.setName(name);
        return this;
    }

    public AssociateDTOBuilder withLastName(String lastName) {
        associateDTO.setLastName(lastName);
        return this;
    }

    public AssociateDTOBuilder withDateOfBirth(LocalDate dateOfBirth) {
        associateDTO.setDateOfBirth(dateOfBirth);
        return this;
    }

    public AssociateDTOBuilder withPhoneNumber(String phoneNumber) {
        associateDTO.setPhoneNumber(phoneNumber);
        return this;
    }

    public AssociateDTOBuilder withEmail(String email) {
        associateDTO.setEmail(email);
        return this;
    }

    public AssociateDTO build() {
        return associateDTO;
    }
}
