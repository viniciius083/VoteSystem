package com.vinicius.vs.voto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.vs.associado.model.Associado;
import com.vinicius.vs.pauta.enumeration.ResultadoPauta;
import com.vinicius.vs.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_voto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResultadoPauta vote;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "associado_id")
    private Associado associado;


    public Voto(Pauta pauta, Associado associado, ResultadoPauta vote) {
        this.setVote(vote);
        this.setPauta(pauta);
        this.setAssociado(associado);
    }
}
