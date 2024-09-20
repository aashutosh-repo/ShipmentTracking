package com.port.tracking.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShipmentTrackingDto {
    private String trackingNumber;
    private String location;
    private String status;
    private LocalDateTime timestamp;
    
}
