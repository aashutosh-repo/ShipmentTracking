package com.port.tracking.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShipmentLocationDto {

    private String containerId;         // Container or shipment ID being tracked
    private String trackingNumber;      // Shipment's tracking number
    private double latitude;            // Latitude from the GPS
    private double longitude;           // Longitude from the GPS
    private String locationDescription; // Optional: human-readable description of the location (e.g., port or city)
    private LocalDateTime timestamp;    // Time when this location data was recorded
}

