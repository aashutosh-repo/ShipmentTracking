package com.port.tracking.mapper;

import com.port.tracking.dto.ShipmentLocationDto;
import com.port.tracking.model.TrackingEvents;

public class ShipmentLocationMapper {

    // Map from TrackingEvents to ShipmentLocationDto
    public static ShipmentLocationDto toShipmentLocationDto(ShipmentLocationDto shipmentLocationDto, TrackingEvents trackingEvent) {
        if (trackingEvent == null) {
            return null;
        }
        shipmentLocationDto.setContainerId(String.valueOf(trackingEvent.getShipmentId()));
        shipmentLocationDto.setTrackingNumber(trackingEvent.getTrackingNumber());
        shipmentLocationDto.setLatitude(trackingEvent.getLatitude());
        shipmentLocationDto.setLongitude(trackingEvent.getLongitude());
        shipmentLocationDto.setLocationDescription(trackingEvent.getLocation());
        shipmentLocationDto.setTimestamp(trackingEvent.getTimestamp());

        return shipmentLocationDto;
    }

    // Map from ShipmentLocationDto to TrackingEvents
    public static TrackingEvents toTrackingEvents(TrackingEvents trackingEvent,ShipmentLocationDto shipmentLocationDto) {
        if (shipmentLocationDto == null) {
            return null;
        }
        trackingEvent.setTrackingNumber(shipmentLocationDto.getTrackingNumber());
        trackingEvent.setLatitude(shipmentLocationDto.getLatitude());
        trackingEvent.setLongitude(shipmentLocationDto.getLongitude());
        trackingEvent.setLocation(shipmentLocationDto.getLocationDescription());
        trackingEvent.setTimestamp(shipmentLocationDto.getTimestamp());
        // Set status or other fields as needed

        return trackingEvent;
    }
}
