package com.vinicius.vs.pauta.model;

import com.vinicius.vs.pauta.dtos.CriarPautaDTO;
import com.vinicius.vs.pauta.enumeration.ResultadoPauta;
import com.vinicius.vs.voto.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pauta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String subject;

    @Enumerated(EnumType.STRING)
    private ResultadoPauta result;

    private LocalDateTime openVote;

    private LocalDateTime closeVote;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pauta")
    private List<Voto> votos = new ArrayList<>();

    private int quantityVotes = 0;


    public Pauta(CriarPautaDTO pautaLiteDTO) {
        this.setSubject(pautaLiteDTO.getSubject());
    }

}
