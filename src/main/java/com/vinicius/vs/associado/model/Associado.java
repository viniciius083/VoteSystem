package com.vinicius.vs.associado.model;

import com.vinicius.vs.associado.dtos.CriarAssociadoDTO;
import com.vinicius.vs.voto.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_associado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cpf não pode ser nulo e deve conter 11 digitos.")
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "Nome não pode ser nulo")
    @Size(max = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "associado")
    private List<Voto> votos = new ArrayList<>();

    public Associado(CriarAssociadoDTO associadoDTO) {
        this.setCpf(associadoDTO.getCpf());
        this.setName(associadoDTO.getName());
    }
}
