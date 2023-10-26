package com.vinicius.vs.associate.controller;

import com.vinicius.vs.associate.dtos.AssociateDTO;
import com.vinicius.vs.associate.dtos.CreateAssociateDTO;
import com.vinicius.vs.associate.service.IAssociateService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associate")
@AllArgsConstructor
@Api(value = "Controlador de Rotas de Associado", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Associado Controller"})
public class AssociateController {

    private final IAssociateService associadoService;

    /**
     * Endpoint para cadastrar um associado.
     *
     * @param associadoDTO objeto para cadastrar um associado
     * @return detalhes do associado cadastrado.
     */
    @PostMapping(produces="application/json", consumes="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public AssociateDTO createAssociate(@RequestBody CreateAssociateDTO associadoDTO){
        return associadoService.createAssociate(associadoDTO);
    }

    /**
     * Endpoint para listar todos os associados cadastrados.
     *
     * @return lista de assosciados cadastrados
     */
    @GetMapping(produces="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<AssociateDTO> listAssociates(){
        return associadoService.listAssociates();
    }

    /**
     * Endpoint para listar um associado.
     *
     * @param id identificador do associado
     * @return informações do associado
     */
    @GetMapping(path = "/{id}", produces="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AssociateDTO listAssociate(@PathVariable long id){
        return new AssociateDTO(associadoService.listAssociates(id));
    }
}
