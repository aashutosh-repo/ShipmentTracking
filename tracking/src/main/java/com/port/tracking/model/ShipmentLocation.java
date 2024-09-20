package com.port.tracking.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ShipmentLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locationId;
    private String containerId;         // Container or shipment ID being tracked
    private String trackingNumber;      // Shipment's tracking number
    private double latitude;            // Latitude from the GPS
    private double longitude;           // Longitude from the GPS
    private String locationDescription; // Optional: human-readable description of the location (e.g., port or city)
    private LocalDateTime timestamp; 
}