package com.challenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

@Component
public class AuditBookingReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(AuditBookingReceiver.class);

    @RabbitListener(queues = "${spring.rabbitmq.queues.booking.audit}")
    public void receivedMessage(Object message) {
        logger.info("Incoming message... " + message.toString());
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
