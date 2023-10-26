package com.vinicius.vs.rabbitmq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.vs.associate.dtos.AssociateDTO;
import com.vinicius.vs.constants.RabbitmqConstantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitmqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public void sendMessageAssociate(AssociateDTO associateDTO){
        try {
            log.info("Enviando mensagem para atualização do Associado");
            String associateJson = this.objectMapper.writeValueAsString(associateDTO);
            this.rabbitTemplate.convertAndSend(RabbitmqConstantes.QEUE_ASSOCIATE, associateJson);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMessageAgenda(String message){
        try {
            log.info("Enviando mensagem de Resultado da pauta.");
            this.rabbitTemplate.convertAndSend(RabbitmqConstantes.QEUE_AGENDA, message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
