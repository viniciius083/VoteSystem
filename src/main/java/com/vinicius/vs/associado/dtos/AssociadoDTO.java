package com.vinicius.vs.associado.dtos;

import com.vinicius.vs.associado.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO {

    private Long id;

    private String cpf;

    private String name;

    public AssociadoDTO(Associado associado) {
        this.setId(associado.getId());
        this.setCpf(associado.getCpf());
        this.setName(associado.getName());
    }
}
