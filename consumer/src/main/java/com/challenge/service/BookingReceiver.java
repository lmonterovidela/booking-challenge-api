package com.challenge.service;

import com.challenge.dto.BookingDto;
import com.challenge.repositories.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookingReceiver implements RabbitListenerConfigurer {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    protected ModelMapper modelMapper;

    abstract void receivedMessage(BookingDto bookingDto);

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
