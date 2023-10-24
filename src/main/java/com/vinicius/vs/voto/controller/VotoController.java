package com.vinicius.vs.voto.controller;


import com.vinicius.vs.voto.dtos.ConfirmacaoVoto;
import com.vinicius.vs.voto.dtos.VotarDTO;
import com.vinicius.vs.voto.service.IVotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
@AllArgsConstructor
public class VotoController {

    private final IVotoService votoService;
    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ConfirmacaoVoto votar(@RequestBody VotarDTO votarDTO){

        return votoService.votar(votarDTO);
    }
}
