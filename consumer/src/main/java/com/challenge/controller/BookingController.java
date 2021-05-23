package com.challenge.controller;

import com.challenge.dto.BookingDto;
import com.challenge.model.Booking;
import com.challenge.repositories.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(value = "/v1")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    protected ModelMapper modelMapper;

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable("id") long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            BookingDto bookingDto = modelMapper.map(booking.get(), BookingDto.class);
            return new ResponseEntity<>(bookingDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
