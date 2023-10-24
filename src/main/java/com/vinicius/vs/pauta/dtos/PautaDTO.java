package com.vinicius.vs.pauta.dtos;

import com.vinicius.vs.pauta.enumeration.ResultadoPauta;
import com.vinicius.vs.pauta.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {

    private long id;

    private String subject;

    private ResultadoPauta result;

    private LocalDateTime openVote;

    private LocalDateTime closeVote;

    public PautaDTO(Pauta pauta) {
        this.setId(pauta.getId());
        this.setSubject(pauta.getSubject());
        this.setResult(pauta.getResult());
        this.setOpenVote(pauta.getOpenVote());
        this.setCloseVote(pauta.getCloseVote());
    }
}
