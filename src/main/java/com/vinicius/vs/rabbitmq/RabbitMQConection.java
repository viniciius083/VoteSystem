package com.vinicius.vs.rabbitmq;

import com.vinicius.vs.constants.RabbitmqConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nameQeue){
        return new Queue(nameQeue, true, false, false);
    }

    private DirectExchange directExchange(String nameExchange) {
        return new DirectExchange(nameExchange);
    }

    private Binding binding(Queue queue, DirectExchange directExchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), directExchange.getName(), null);
    }

    @PostConstruct
    private void createConnections(){
        Queue queueAgenda   = this.queue(RabbitmqConstantes.QEUE_AGENDA);
        DirectExchange directExchangeAgenda = this.directExchange(RabbitmqConstantes.EXCHANGE_AGENDA);
        Binding bindingAgenda   = this.binding(queueAgenda, directExchangeAgenda);

        Queue queueAssociate   = this.queue(RabbitmqConstantes.QEUE_ASSOCIATE);
        DirectExchange directExchangeAssociate = this.directExchange(RabbitmqConstantes.EXCHANGE_ASSOCIATE);
        Binding bindingAssociate   = this.binding(queueAssociate, directExchangeAssociate);

        this.amqpAdmin.declareQueue(queueAgenda);
        this.amqpAdmin.declareExchange(directExchangeAgenda);
        this.amqpAdmin.declareBinding(bindingAgenda);

        this.amqpAdmin.declareQueue(queueAssociate);
        this.amqpAdmin.declareExchange(directExchangeAssociate);
        this.amqpAdmin.declareBinding(bindingAssociate);
    }
}
