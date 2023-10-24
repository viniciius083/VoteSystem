package com.vinicius.vs.associado.service;

import com.vinicius.vs.associado.dtos.AssociadoDTO;
import com.vinicius.vs.associado.dtos.CriarAssociadoDTO;
import com.vinicius.vs.associado.model.Associado;

import java.util.List;

public interface IAssociadoService {
    AssociadoDTO criarAssociado(CriarAssociadoDTO associadoDTO);

    List<AssociadoDTO> listarAssociados();

    Associado listarAssociado(long id);
}
