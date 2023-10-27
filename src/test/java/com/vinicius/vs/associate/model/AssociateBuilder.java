package com.vinicius.vs.associate.model;

import com.vinicius.vs.vote.model.Vote;

import java.time.LocalDate;
import java.util.List;

public final class AssociateBuilder {
    private Associate associate;

    private AssociateBuilder() {
        associate = new Associate();
    }

    public static AssociateBuilder anAssociate() {
        AssociateBuilder builder = new AssociateBuilder();
        builder.associate = new Associate();
        builder.associate.setId(1L);
        builder.associate.setCpf("12291783416");
        builder.associate.setName("Vinicius");
        return builder;
    }

    public AssociateBuilder withId(Long id) {
        associate.setId(id);
        return this;
    }

    public AssociateBuilder withCpf(String cpf) {
        associate.setCpf(cpf);
        return this;
    }

    public AssociateBuilder withName(String name) {
        associate.setName(name);
        return this;
    }

    public AssociateBuilder withLastName(String lastName) {
        associate.setLastName(lastName);
        return this;
    }

    public AssociateBuilder withDateOfBirth(LocalDate dateOfBirth) {
        associate.setDateOfBirth(dateOfBirth);
        return this;
    }

    public AssociateBuilder withPhoneNumber(String phoneNumber) {
        associate.setPhoneNumber(phoneNumber);
        return this;
    }

    public AssociateBuilder withEmail(String email) {
        associate.setEmail(email);
        return this;
    }

    public AssociateBuilder withVotes(List<Vote> votes) {
        associate.setVotes(votes);
        return this;
    }

    public Associate build() {
        return associate;
    }
}
