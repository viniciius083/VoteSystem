package com.vinicius.vs.associate.dtos;

public final class CreateAssociateDTOBuilder {
    private CreateAssociateDTO createAssociateDTO;

    private CreateAssociateDTOBuilder() {
        createAssociateDTO = new CreateAssociateDTO();
    }

    public static CreateAssociateDTOBuilder aCreateAssociateDTO() {
        CreateAssociateDTOBuilder builder = new CreateAssociateDTOBuilder();
        builder.createAssociateDTO = new CreateAssociateDTO();
        builder.createAssociateDTO.setCpf("12291783416");
        builder.createAssociateDTO.setName("Vinicius");
        return builder;
    }

    public CreateAssociateDTOBuilder withCpf(String cpf) {
        createAssociateDTO.setCpf(cpf);
        return this;
    }

    public CreateAssociateDTOBuilder withName(String name) {
        createAssociateDTO.setName(name);
        return this;
    }

    public CreateAssociateDTO build() {
        return createAssociateDTO;
    }
}
