package com.vinicius.vs.voto.service;

import com.vinicius.vs.voto.dtos.ConfirmacaoVoto;
import com.vinicius.vs.voto.dtos.VotarDTO;

public interface IVotoService {
    ConfirmacaoVoto votar(VotarDTO votarDTO);
}
