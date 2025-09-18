package com.online_ticket_booking_system.ticket_booking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`from`")
    private String from;

    private String destination;
    private double distance;  
    private String duration;

    public Route() {}

    public Route(String from, String destination) {
        this.from = from;
        this.destination = destination;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }   // ✅ Added this

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
