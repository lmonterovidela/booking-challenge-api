package com.challenge.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class TripWaypoint implements Serializable {

    private String locality;
    private String latitude;
    private String longitude;

    @Override
    public String toString() {
        return "Point{" +
                "locality='" + locality + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}