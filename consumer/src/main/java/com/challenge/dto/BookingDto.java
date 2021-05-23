package com.challenge.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Component
@Data
public class BookingDto implements Serializable {

    private long id;
    private String name;
    private String contactNumber;
    private Date pickupTime;
    private Boolean asap;
    private int waitingTime;
    private int numberOfPassengers;
    private double price;
    private int rating;
    private Date lastModifiedOn;
    private Date createdOn;
    private Set<TripWaypointDto> tripWaypoints;

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
                ", points=" + tripWaypoints +
                '}';
    }
}