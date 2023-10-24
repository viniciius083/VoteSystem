package com.vinicius.vs.associado.controller;

import com.vinicius.vs.associado.dtos.AssociadoDTO;
import com.vinicius.vs.associado.dtos.CriarAssociadoDTO;
import com.vinicius.vs.associado.model.Associado;
import com.vinicius.vs.associado.service.IAssociadoService;
import com.vinicius.vs.pauta.dtos.CriarPautaDTO;
import com.vinicius.vs.pauta.dtos.PautaDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associado")
@AllArgsConstructor
public class AssociadoController{

    private final IAssociadoService associadoService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoDTO criarAssociado(@RequestBody CriarAssociadoDTO associadoDTO){
        return associadoService.criarAssociado(associadoDTO);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<AssociadoDTO> listarAssociados(){
        return associadoService.listarAssociados();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AssociadoDTO listarAssociado(@PathVariable long id){
        return new AssociadoDTO(associadoService.listarAssociado(id));
    }
}
