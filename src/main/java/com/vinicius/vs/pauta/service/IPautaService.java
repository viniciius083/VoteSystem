package com.vinicius.vs.pauta.service;

import com.vinicius.vs.pauta.dtos.AbrirVotacaoDTO;
import com.vinicius.vs.pauta.dtos.CriarPautaDTO;
import com.vinicius.vs.pauta.dtos.PautaDTO;
import com.vinicius.vs.pauta.model.Pauta;
import com.vinicius.vs.voto.dtos.VotarDTO;

import java.util.List;

public interface IPautaService {
    PautaDTO criarPauta(CriarPautaDTO pautaLiteDTO);

    PautaDTO abrirVotacao(AbrirVotacaoDTO abrirVotacaoDTO);

    List<PautaDTO> listarPautas();

    Pauta listarPauta(long id);

}
