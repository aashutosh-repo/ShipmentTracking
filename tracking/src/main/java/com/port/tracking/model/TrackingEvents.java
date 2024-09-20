package com.port.tracking.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TrackingEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private Long shipmentId;
    private String trackingNumber;
    private String location;   // Can be a port, city, or latitude/longitude from GPS
    private double latitude;
    private double longitude;
    private String status;            // "In Transit", "At Port", "Customs Cleared", etc.
    private LocalDateTime timestamp;  // When the status was updated
    
    // Getters and Setters
}
