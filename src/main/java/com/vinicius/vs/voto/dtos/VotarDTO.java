package com.vinicius.vs.voto.dtos;

import com.vinicius.vs.pauta.enumeration.ResultadoPauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotarDTO {

    private long associateId;

    private ResultadoPauta vote;

    private long meetingId;
}
