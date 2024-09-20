package com.port.tracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.port.tracking.dto.ShipmentLocationDto;
import com.port.tracking.extapi.ShipmentGpsService;
import com.port.tracking.mapper.ShipmentLocationMapper;
import com.port.tracking.model.Shipment;
import com.port.tracking.model.TrackingEvents;
import com.port.tracking.repository.ShipmentRepository;
import com.port.tracking.repository.TrackingEventRepository;

@Service
public class ShipmentTrackingService {

    @Autowired
    private ShipmentRepository shipmentRepository;
    
    @Autowired
    private TrackingEventRepository trackingEventRepository;
    
    @Autowired
    private ShipmentGpsService gpsService;

    // Create a new shipment
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    // Update the tracking event of a shipment
    public TrackingEvents addTrackingEvent(Long shipmentId,ShipmentLocationDto locationDetails) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                              .orElseThrow(() -> new RuntimeException("Shipment not found"));

        TrackingEvents event = new TrackingEvents();
        event.setShipmentId(shipmentId);
        event = ShipmentLocationMapper.toTrackingEvents(event,locationDetails);
        event.setTrackingNumber(shipment.getTrackingNumber());
//        event.setLocation(location);
//        event.setStatus(status);
//        event.setTimestamp(LocalDateTime.now());

        return trackingEventRepository.save(event);
    }

    // Get shipment details by tracking number
    public Shipment getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    // Get all tracking events for a shipment
    public List<TrackingEvents> getTrackingHistory(String trackingNumber) {
        return trackingEventRepository.findByTrackingNumberOrderByTimestampAsc(trackingNumber);
    }
    
    
    public TrackingEvents updateShipmentLocationExtApi(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                              .orElseThrow(() -> new RuntimeException("Shipment not found"));

        // Fetch GPS location data
        ShipmentLocationDto locationDto = gpsService.getGpsLocation(shipment.getContainerId());

        // Create a new tracking event with the GPS location details
        TrackingEvents event = new TrackingEvents();
        
        event = ShipmentLocationMapper.toTrackingEvents(event, locationDto);
        event.setShipmentId(shipmentId);
        event.setTrackingNumber(shipment.getTrackingNumber());
        
//        event.setLocation(locationDto.getLocationDescription()); // Using human-readable location
        event.setStatus("In Transit"); // You can dynamically set this based on business rules
//        event.setTimestamp(locationDto.getTimestamp());
//        event.setLatitude(locationDto.getLatitude());

        return trackingEventRepository.save(event);
    }
}

