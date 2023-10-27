package com.vinicius.vs.associate.service.impl;

import com.vinicius.vs.associate.dtos.AssociateDTO;
import com.vinicius.vs.associate.dtos.RandomInfoDTO;
import com.vinicius.vs.associate.model.Associate;
import com.vinicius.vs.associate.repository.IAssociateRepository;
import com.vinicius.vs.exceptions.errors.DataIntegratyViolationException;
import com.vinicius.vs.feign.RandomDataApi;
import com.vinicius.vs.rabbitmq.service.RabbitmqService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.vinicius.vs.associate.dtos.AssociateDTOBuilder.anAssociateDTO;
import static com.vinicius.vs.associate.dtos.CreateAssociateDTOBuilder.aCreateAssociateDTO;
import static com.vinicius.vs.associate.dtos.RandomInfoDTOBuilder.aRandomInfoDTO;
import static com.vinicius.vs.associate.model.AssociateBuilder.anAssociate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociateServiceImplTest {

    @InjectMocks
    private AssociateServiceImpl associateService;

    @Mock
    private IAssociateRepository associateRepository;

    @Mock
    private RandomDataApi randomDataApi;

    @Mock
    private RabbitmqService rabbitmqService;

    @Test
    @DisplayName("Tentar salvar quando já existe um associado com o mesmo CPF cadastrado.")
    void createAssociateExisting() {
        String cpf = "12291783416";

        Optional<Associate> associateOptional = Optional.ofNullable(anAssociate().build());
        when(associateRepository.findByCpf(cpf)).thenReturn(associateOptional);

        DataIntegratyViolationException ex = Assertions.assertThrows(DataIntegratyViolationException.class, () -> associateService.createAssociate(aCreateAssociateDTO().build()));

        assertTrue(ex.getMessage().contains("CPF já existente no nosso banco!"));
        verify(associateRepository, never()).save(any());
        verify(rabbitmqService, never()).sendMessageAssociate(any());
    }

    @Test
    @DisplayName("Salvar um associado")
    void createAssociateSuccessful() {
        String cpf = "12291783416";
        String name = "Vinicius";

        when(associateRepository.findByCpf(cpf)).thenReturn(Optional.empty());
        when(associateRepository.save(any())).thenReturn(anAssociate().build());
        doNothing().when(rabbitmqService).sendMessageAssociate(any());

        AssociateDTO associateReturn = associateService.createAssociate(aCreateAssociateDTO().build());

        assertEquals(associateReturn.getCpf(), cpf);
        assertEquals(associateReturn.getName(), name);
        verify(associateRepository, times(1)).save(any());

        ArgumentCaptor<AssociateDTO> associateRabbit = ArgumentCaptor.forClass(AssociateDTO.class);
        verify(rabbitmqService, times(1)).sendMessageAssociate(associateRabbit.capture());
        assertEquals(associateReturn.getId(), associateRabbit.getValue().getId());
        assertEquals(associateReturn.getCpf(), associateRabbit.getValue().getCpf());
        assertEquals(associateReturn.getName(), associateRabbit.getValue().getName());
    }

    @Test
    @DisplayName("Listar todos os associados.")
    void listAssociates() {
        Associate associate = anAssociate().build();
        when(associateRepository.findAll()).thenReturn(Collections.singletonList(associate));

        List<AssociateDTO> associateList  = associateService.listAssociates();

    }

    @Test
    @DisplayName("Listar um associado existente")
    void testListAssociates() {
        Associate associate = anAssociate().build();
        associate.update(aRandomInfoDTO().build());
        when(associateRepository.findById(anyLong())).thenReturn(Optional.of(associate));

        Associate associateReturn = associateService.listAssociate(1L);

        assertEquals(associate.getId(), associateReturn.getId());
        assertEquals(associate.getCpf(), associateReturn.getCpf());
        assertEquals(associate.getName(), associateReturn.getName());
        assertEquals(associate.getLastName(), associateReturn.getLastName());
        assertEquals(associate.getDateOfBirth(), associateReturn.getDateOfBirth());
        assertEquals(associate.getPhoneNumber(), associateReturn.getPhoneNumber());
        assertEquals(associate.getEmail(), associateReturn.getEmail());
    }

    @Test
    @DisplayName("Atualizar um associado inexistente no banco.")
    void updateAssociateNonExistent() {
        when(associateRepository.findById(anyLong())).thenReturn(Optional.empty());

        associateService.updateAssociate(anAssociateDTO().build());

        verify(randomDataApi, never()).searchRandomData();
        verify(associateRepository, never()).save(any());
    }

    @Test
    @DisplayName("Atualizar o associado com dados da Random-data-api")
    void updateAssociateSuccessful() {
        Optional<Associate> associateOptional = Optional.ofNullable(anAssociate().build());
        RandomInfoDTO randomInfoDTO = aRandomInfoDTO().build();
        when(associateRepository.findById(anyLong())).thenReturn(associateOptional);
        when(randomDataApi.searchRandomData()).thenReturn(randomInfoDTO);

        associateService.updateAssociate(anAssociateDTO().build());

        verify(randomDataApi, times(1)).searchRandomData();

        ArgumentCaptor<Associate> associateUpdate = ArgumentCaptor.forClass(Associate.class);
        verify(associateRepository, times(1)).save(associateUpdate.capture());

        assertEquals(associateUpdate.getValue().getLastName(), randomInfoDTO.getLastName());
        assertEquals(associateUpdate.getValue().getDateOfBirth(),randomInfoDTO.getDateOfBirth());
        assertEquals(associateUpdate.getValue().getPhoneNumber(), randomInfoDTO.getPhoneNumber());
        assertEquals(associateUpdate.getValue().getEmail(), randomInfoDTO.getEmail());
    }
}