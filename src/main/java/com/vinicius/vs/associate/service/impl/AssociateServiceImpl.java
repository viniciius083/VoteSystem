package com.vinicius.vs.associate.service.impl;

import com.vinicius.vs.associate.dtos.AssociateDTO;
import com.vinicius.vs.associate.dtos.CreateAssociateDTO;
import com.vinicius.vs.associate.model.Associate;
import com.vinicius.vs.associate.repository.IAssociateRepository;
import com.vinicius.vs.associate.service.IAssociateService;
import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.exceptions.errors.ObjectNotFoundException;
import com.vinicius.vs.feign.RandomDataApi;
import com.vinicius.vs.rabbitmq.service.RabbitmqService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssociateServiceImpl implements IAssociateService {

    private final IAssociateRepository associateRepository;

    private final RandomDataApi randomDataApi;

    private final RabbitmqService rabbitmqService;

    /**
     * Método para cadastrar um associado no banco de dados, é necessário informar um NOME e CPF.
     *
     * @param createAssociateDTO dados do associado a ser cadastrado
     * @return detalhes do associado cadastrado
     */
    @Override
    public AssociateDTO createAssociate(CreateAssociateDTO createAssociateDTO) {
        Associate associate = new Associate(createAssociateDTO);
        if(associateRepository.findByCpf(createAssociateDTO.getCpf()).isPresent()){
            throw new DataIntegratyViolationException("CPF já existente no nosso banco!");
        }
        associateRepository.save(associate);
        rabbitmqService.sendMessageAssociate(new AssociateDTO(associate));

        return new AssociateDTO(associate);
    }


    /**
     * Método para listar todos os associados cadastrados.
     *
     * @return lista contendo todos os associados cadastrados.
     */
    @Override
    public List<AssociateDTO> listAssociates() {
        return associateRepository.findAll().stream().map(AssociateDTO::new).collect(Collectors.toList());
    }

    /**
     * Método para listar um associado através do identificador.
     *
     * @param id identificador do associado
     * @return detalhes do associado identificado.
     */
    @Override
    public Associate listAssociate(long id) {
        return associateRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Associado(a) não encontrado(a)!"));
    }


    @Override
    public void updateAssociate(AssociateDTO associateDTO){
        Associate associate = associateRepository.findById(associateDTO.getId()).orElse(null);
        if (associate != null) {
            associate.update(randomDataApi.searchRandomData());
            associateRepository.save(associate);
        }
    }
}
