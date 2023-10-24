package com.vinicius.vs.associado.service.impl;

import com.vinicius.vs.associado.dtos.AssociadoDTO;
import com.vinicius.vs.associado.dtos.CriarAssociadoDTO;
import com.vinicius.vs.associado.model.Associado;
import com.vinicius.vs.associado.repository.IAssociadoRepository;
import com.vinicius.vs.associado.service.IAssociadoService;
import com.vinicius.vs.exceptions.errors.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssociadoServiceImpl implements IAssociadoService {

    private final IAssociadoRepository associadoRepository;
    @Override
    public AssociadoDTO criarAssociado(CriarAssociadoDTO associadoDTO) {
        Associado associado = new Associado(associadoDTO);
        //Validar CPF para verificar se é valido.
        associadoRepository.save(associado);
        return new AssociadoDTO(associado);
    }

    @Override
    public List<AssociadoDTO> listarAssociados() {
        return associadoRepository.findAll().stream().map(AssociadoDTO::new).collect(Collectors.toList());
    }

    @Override
    public Associado listarAssociado(long id) {
        return associadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Associado(a) não encontrado(a)!"));
    }


}
