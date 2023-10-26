package com.vinicius.vs.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.vs.associate.dtos.AssociateDTO;
import com.vinicius.vs.constants.RabbitmqConstantes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AssociateConsumer {
    @RabbitListener(queues = RabbitmqConstantes.QEUE_ASSOCIATE)
    private void consumer(String message) throws JsonProcessingException {
        AssociateDTO associateDTO = new ObjectMapper().readValue(message, AssociateDTO.class);
        System.out.println("Novo cadastro: "+ associateDTO.getCpf());
    }
}
