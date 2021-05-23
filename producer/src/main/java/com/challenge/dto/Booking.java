package com.challenge.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@Data
public class Booking implements Serializable {

    private long id;
    private String name;
    private String contactNumber;
    private Date pickupTime;
    private Boolean asap;
    private int waitingTime;
    private int numberOfPassengers;
    private double price;
    private int rating;
    private List<TripWaypoint> tripWaypoints;

    @Override
    public String toString() {
        return "Booking{" +
                "name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", pickupTime=" + pickupTime +
                ", asap=" + asap +
                ", waitingTime=" + waitingTime +
                ", numberOfPassengers=" + numberOfPassengers +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}