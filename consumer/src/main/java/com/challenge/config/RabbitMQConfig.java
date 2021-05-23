package com.challenge.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.exchanges.message}")
    private String messageExchange;

    @Value("${spring.rabbitmq.exchanges.booking}")
    private String bookingExchange;

    @Value("${spring.rabbitmq.queues.booking.audit}")
    private String auditBookingQueue;

    @Value("${spring.rabbitmq.queues.booking.add}")
    private String addBookingQueue;

    @Value("${spring.rabbitmq.queues.booking.edit}")
    private String editBookingQueue;

    @Value("${spring.rabbitmq.queues.booking.delete}")
    private String deleteBookingQueue;

    @Value("${spring.rabbitmq.keys.booking.add}")
    private String addBookingKey;

    @Value("${spring.rabbitmq.keys.booking.edit}")
    private String editBookingKey;

    @Value("${spring.rabbitmq.keys.booking.delete}")
    private String deleteBookingKey;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    // Create queues
    @Bean
    Queue auditQueue() {
        return new Queue(auditBookingQueue, true);
    }

    @Bean
    Queue addBookingQueue() {
        return new Queue(addBookingQueue, true);
    }

    @Bean
    Queue editBookingQueue() {
        return new Queue(editBookingQueue, true);
    }

    @Bean
    Queue deleteBookingQueue() {
        return new Queue(deleteBookingQueue, true);
    }

    // Create Exchange
    @Bean
    Exchange messageExchange() {
        return ExchangeBuilder.fanoutExchange(messageExchange).durable(true).build();
    }

    @Bean
    Exchange bookingExchange() {
        return ExchangeBuilder.directExchange(bookingExchange).durable(true).build();
    }

    // Binders
    @Bean
    Binding bindingExchanges(Exchange bookingExchange, Exchange messageExchange) {
        return BindingBuilder.bind(bookingExchange).to((FanoutExchange) messageExchange);
    }

    @Bean
    Binding bindingAuditQueue(Queue auditQueue, Exchange messageExchange) {
        return BindingBuilder.bind(auditQueue).to((FanoutExchange) messageExchange);
    }

    @Bean
    Binding bindingAddBookingQueue(Queue addBookingQueue, Exchange bookingExchange) {
        return BindingBuilder.bind(addBookingQueue).to(bookingExchange).with(addBookingKey).noargs();
    }

    @Bean
    Binding bindingEditBookingQueue(Queue editBookingQueue, Exchange bookingExchange) {
        return BindingBuilder.bind(editBookingQueue).to(bookingExchange).with(editBookingKey).noargs();
    }

    @Bean
    Binding bindingDeleteBookingQueue(Queue deleteBookingQueue, Exchange bookingExchange) {
        return BindingBuilder.bind(deleteBookingQueue).to(bookingExchange).with(deleteBookingKey).noargs();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
