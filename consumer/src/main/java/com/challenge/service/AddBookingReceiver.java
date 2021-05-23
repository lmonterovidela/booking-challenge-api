package com.challenge.service;

import com.challenge.dto.BookingDto;
import com.challenge.model.Booking;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

@Component
public class AddBookingReceiver extends BookingReceiver {

    @RabbitListener(queues = "${spring.rabbitmq.queues.booking.add}")
    public void receivedMessage(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        try {
            bookingRepository.save(booking);
        } catch (Exception e) {
            //TODO: discard, send to deadLetter, retry?
        }
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
