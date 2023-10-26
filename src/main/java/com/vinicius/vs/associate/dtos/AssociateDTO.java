package com.vinicius.vs.associate.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinicius.vs.associate.model.Associate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociateDTO {

    private Long id;

    private String cpf;

    private String name;

    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String email;

    public AssociateDTO(Associate associate) {
        this.setId(associate.getId());
        this.setCpf(associate.getCpf());
        this.setName(associate.getName());
        this.setLastName(associate.getLastName());
        this.setDateOfBirth(associate.getDateOfBirth());
        this.setPhoneNumber(associate.getPhoneNumber());
        this.setEmail(associate.getEmail());
    }
}
