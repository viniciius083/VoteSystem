package com.vinicius.vs.associate.dtos;

import java.time.LocalDate;

public final class RandomInfoDTOBuilder {
    private RandomInfoDTO randomInfoDTO;

    private RandomInfoDTOBuilder() {
        randomInfoDTO = new RandomInfoDTO();
    }

    public static RandomInfoDTOBuilder aRandomInfoDTO() {
        RandomInfoDTOBuilder builder = new RandomInfoDTOBuilder();
        builder.randomInfoDTO = new RandomInfoDTO();
        builder.randomInfoDTO.setLastName("Nascimento");
        builder.randomInfoDTO.setDateOfBirth(LocalDate.of(1999,4,1));
        builder.randomInfoDTO.setPhoneNumber("+55 83 9 9929-3182");
        builder.randomInfoDTO.setEmail("viniciius83@gmail.com");
        return builder;

    }

    public RandomInfoDTOBuilder withLastName(String lastName) {
        randomInfoDTO.setLastName(lastName);
        return this;
    }

    public RandomInfoDTOBuilder withDateOfBirth(LocalDate dateOfBirth) {
        randomInfoDTO.setDateOfBirth(dateOfBirth);
        return this;
    }

    public RandomInfoDTOBuilder withPhoneNumber(String phoneNumber) {
        randomInfoDTO.setPhoneNumber(phoneNumber);
        return this;
    }

    public RandomInfoDTOBuilder withEmail(String email) {
        randomInfoDTO.setEmail(email);
        return this;
    }

    public RandomInfoDTO build() {
        return randomInfoDTO;
    }
}
