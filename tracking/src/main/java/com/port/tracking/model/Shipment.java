package com.port.tracking.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    
    private String trackingNumber;     // Unique tracking number
    private String containerId;        // Container ID for port shipments
    private String originPort;
    private String destinationPort;
    private LocalDate shippingDate;
    private LocalDate estimatedArrivalDate;
    private LocalDate actualArrivalDate;
    private String currentStatus;      // e.g., "In Transit", "At Port", "Cleared Customs", etc.
    private String transportMode;      // e.g., Sea, Road, Rail, Air

}

