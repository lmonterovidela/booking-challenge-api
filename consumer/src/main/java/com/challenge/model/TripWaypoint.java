package com.challenge.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "waypoint")
public class TripWaypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String locality;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;
}