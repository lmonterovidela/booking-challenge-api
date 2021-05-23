package com.challenge.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private Date pickupTime;

    @Column(nullable = false)
    private Boolean asap;

    @Column(nullable = false)
    private int waitingTime;

    @Column(nullable = false)
    private int numberOfPassengers;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int rating;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    @Column
    private Date lastModifiedOn;


    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<TripWaypoint> tripWaypoints;
}