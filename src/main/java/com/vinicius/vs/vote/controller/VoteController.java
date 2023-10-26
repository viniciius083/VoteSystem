package com.vinicius.vs.vote.controller;


import com.vinicius.vs.vote.dtos.VoteConfirmationDTO;
import com.vinicius.vs.vote.dtos.VoteDTO;
import com.vinicius.vs.vote.service.IVoteService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
@AllArgsConstructor
@Api(value = "Controlador de Rotas de Voto", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Voto Controller"})
public class VoteController {

    private final IVoteService voteService;

    /**
     * Endpoint para realizar votação em uma pauta.
     *
     * @param voteDTO objeto necessário para realizar votação
     * @return confirmação do voto, contendo o nome, voto e assunto da pauta
     */
    @PostMapping(produces="application/json", consumes="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public VoteConfirmationDTO vote(@RequestBody VoteDTO voteDTO){

        return voteService.vote(voteDTO);
    }
}
