package com.challenge.service;

import com.challenge.dto.BookingDto;
import com.challenge.model.Booking;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookingReceiver extends BookingReceiver {

    @RabbitListener(queues = "${spring.rabbitmq.queues.booking.delete}")
    public void receivedMessage(BookingDto bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        try {
            bookingRepository.delete(booking);
        } catch (Exception e) {
            //TODO: discard, send to deadLetter, retry?
        }
    }
}
