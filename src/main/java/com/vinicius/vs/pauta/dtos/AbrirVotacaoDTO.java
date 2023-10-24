package com.vinicius.vs.pauta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbrirVotacaoDTO {

    private long pautaId;
    private int time;
}
