package com.vinicius.vs.pauta.service.impl;

import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.exceptions.errors.ObjectNotFoundException;
import com.vinicius.vs.pauta.dtos.AbrirVotacaoDTO;
import com.vinicius.vs.pauta.dtos.CriarPautaDTO;
import com.vinicius.vs.pauta.dtos.PautaDTO;
import com.vinicius.vs.voto.dtos.VotarDTO;
import com.vinicius.vs.pauta.model.Pauta;
import com.vinicius.vs.pauta.repository.IPautaRepository;
import com.vinicius.vs.pauta.service.IPautaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PautaServiceImpl implements IPautaService {

    private IPautaRepository pautaRepository;

    @Override
    public PautaDTO criarPauta(CriarPautaDTO pautaLiteDTO) {
        Pauta pauta = new Pauta(pautaLiteDTO);
        pautaRepository.save(pauta);
        return new PautaDTO(pauta);
    }

    @Override
    public PautaDTO abrirVotacao(AbrirVotacaoDTO abrirVotacaoDTO) {
        Pauta pauta = pautaRepository.findById(abrirVotacaoDTO.getPautaId()).orElseThrow(() -> new ObjectNotFoundException("Pauta não encontrada!"));

        if(pauta.getOpenVote() != null){
            throw new DataIntegratyViolationException("A Pauta já foi aberta!");
        };


        pauta.setOpenVote(LocalDateTime.now());
        pauta.setCloseVote(LocalDateTime.now().plusMinutes(abrirVotacaoDTO.getTime()));
        pautaRepository.save(pauta);

        return new PautaDTO(pauta);
    }

    @Override
    public List<PautaDTO> listarPautas() {
        return pautaRepository.findAll().stream().map(PautaDTO::new).collect(Collectors.toList());
    }

    @Override
    public Pauta listarPauta(long id) {
        return pautaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pauta não encontrada!"));
    }



}
