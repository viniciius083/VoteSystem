package com.vinicius.vs.agenda.controller;

import com.vinicius.vs.agenda.dtos.OpenVoteDTO;
import com.vinicius.vs.agenda.dtos.CreateAgendaDTO;
import com.vinicius.vs.agenda.dtos.AgendaDTO;
import com.vinicius.vs.agenda.service.IAgendaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
@AllArgsConstructor
@Api(value = "Controlador de Rotas de Pauta", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Pauta Controller"})
public class AgendaController {

    private final IAgendaService agendaService;

    /**
     * Endpoint para criar uma pauta para votação.
     *
     * @param createAgendaDTO objeto para cadastrar uma pauta
     * @return detalhes da pauta cadastrada.
     */

    @PostMapping(produces="application/json", consumes="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public AgendaDTO createAgenda(@RequestBody CreateAgendaDTO createAgendaDTO){
        return agendaService.createAgenda(createAgendaDTO);
    }

    /**
     * Endpoint para abrir a votação de uma pauta.
     *
     * @param openVoteDTO objeto para abrir votação da pauta
     * @return detalhes da pauta que foi aberta a votação.
     */
    @PostMapping(path = "/open-vote", produces="application/json", consumes="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AgendaDTO openVote(@RequestBody OpenVoteDTO openVoteDTO){
        return agendaService.openVote(openVoteDTO);
    }


    /**
     *  Endpoint que trás todas as pautas cadastradas.
     *
     * @return lista de informações das pautas.
     */
    @GetMapping(produces="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<AgendaDTO> listAgendas(){
        return agendaService.listAgendas();
    }

    /**
     * Endpoint para buscar uma pauta no banco.
     *
     * @param id identificador da pauta
     * @return informações da pauta
     */
    @GetMapping(path = "/{id}", produces="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AgendaDTO listAgenda(@PathVariable long id){
        return new AgendaDTO(agendaService.listAgenda(id));
    }

}
