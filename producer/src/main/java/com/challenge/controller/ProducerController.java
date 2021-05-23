package com.challenge.controller;

import com.challenge.dto.Booking;
import com.challenge.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class ProducerController {

    private RabbitMqSender rabbitMqSender;

    @Value("${spring.rabbitmq.keys.booking.add}")
    private String addBookingKey;

    @Value("${spring.rabbitmq.keys.booking.edit}")
    private String editBookingKey;

    @Value("${spring.rabbitmq.keys.booking.delete}")
    private String deleteBookingKey;

    @Autowired
    public ProducerController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @PostMapping(value = "/bookings")
    public ResponseEntity addBooking(@RequestBody Booking booking) {
        rabbitMqSender.send(booking, addBookingKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity updateBooking(@PathVariable("id") long id, @RequestBody Booking booking) {
        booking.setId(id);
        rabbitMqSender.send(booking, editBookingKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity deleteBooking(@PathVariable("id") long id) {
        Booking booking = new Booking();
        booking.setId(id);
        rabbitMqSender.send(booking, deleteBookingKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
