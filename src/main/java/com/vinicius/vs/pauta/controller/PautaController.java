package com.vinicius.vs.pauta.controller;

import com.vinicius.vs.pauta.dtos.AbrirVotacaoDTO;
import com.vinicius.vs.pauta.dtos.CriarPautaDTO;
import com.vinicius.vs.pauta.dtos.PautaDTO;
import com.vinicius.vs.voto.dtos.VotarDTO;
import com.vinicius.vs.pauta.service.IPautaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping("/pauta")
@AllArgsConstructor
public class PautaController {

    private final IPautaService pautaService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PautaDTO criarPauta(@RequestBody CriarPautaDTO pautaLiteDTO){
        return pautaService.criarPauta(pautaLiteDTO);
    }

    @PostMapping("/abrir")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PautaDTO abrirVotacao(@RequestBody AbrirVotacaoDTO abrirVotacaoDTO){
        return pautaService.abrirVotacao(abrirVotacaoDTO);
    }


    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<PautaDTO> listarPautas(){
        return pautaService.listarPautas();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "this is the reason")
    public PautaDTO listarPauta(@PathVariable long id){
        return new PautaDTO(pautaService.listarPauta(id));
    }

}
