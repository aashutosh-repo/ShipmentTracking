package com.port.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.port.tracking.dto.ShipmentLocationDto;
import com.port.tracking.model.Shipment;
import com.port.tracking.model.TrackingEvents;
import com.port.tracking.service.ShipmentTrackingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Shipment Tracking Controller", description = "APIs for managing shipment tracking and event updates")
@RestController
@RequestMapping("/api/shipments")
public class ShipmentTrackingController {

    @Autowired
    private ShipmentTrackingService shipmentTrackingService;

    /**
     * Create a new shipment.
     *
     * @param shipment A JSON body with the shipment details.
     * @return The created Shipment object.
     */
    @Operation(summary = "Create Shipment API", description = "REST API to create a new shipment")
    @ApiResponse(responseCode = "201", description = "Http Status CREATED")
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment createdShipment = shipmentTrackingService.createShipment(shipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShipment);
    }

    /**
     * Manually add a tracking event for a shipment.
     *
     * @param shipmentId The ID of the shipment being updated.
     * @param locationDetails A JSON body with the location and status details.
     * @return The created TrackingEvents object.
     */
    @Operation(summary = "Add Tracking Event Manually", description = "Manually update the status and location of a shipment")
    @ApiResponse(responseCode = "200", description = "Tracking event added successfully")
    @PostMapping("/updateShipmentStatus")
    public ResponseEntity<TrackingEvents> addTrackingEventManual(@RequestParam Long shipmentId, @RequestBody ShipmentLocationDto locationDetails) {
        TrackingEvents trackingEvent = shipmentTrackingService.addTrackingEvent(shipmentId, locationDetails);
        return ResponseEntity.ok(trackingEvent);
    }

    /**
     * Get shipment details by tracking number.
     *
     * @param trackingNumber The tracking number of the shipment.
     * @return The Shipment object with details.
     */
    @Operation(summary = "Get Shipment by Tracking Number", description = "Fetch shipment details by its tracking number")
    @ApiResponse(responseCode = "200", description = "Shipment details fetched successfully")
    @GetMapping("/{trackingNumber}")
    public ResponseEntity<Shipment> getShipment(@PathVariable String trackingNumber) {
        Shipment shipment = shipmentTrackingService.getShipmentByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(shipment);
    }

    /**
     * Get all tracking events (history) for a shipment by its tracking number.
     *
     * @param trackingNumber The tracking number of the shipment.
     * @return A list of TrackingEvents objects.
     */
    @Operation(summary = "Get Shipment Tracking History", description = "Fetch the tracking event history for a shipment by its tracking number")
    @ApiResponse(responseCode = "200", description = "Tracking history fetched successfully")
    @GetMapping("/{trackingNumber}/history")
    public ResponseEntity<List<TrackingEvents>> getTrackingHistory(@PathVariable String trackingNumber) {
        List<TrackingEvents> trackingHistory = shipmentTrackingService.getTrackingHistory(trackingNumber);
        return ResponseEntity.ok(trackingHistory);
    }

    /**
     * Update the shipment location using an external API.
     *
     * @param shipmentId The ID of the shipment.
     * @return The updated TrackingEvents object.
     */
    @Operation(summary = "Update Shipment Location via External API", description = "Update shipment location details using an external API service")
    @ApiResponse(responseCode = "200", description = "Shipment location updated successfully")
    @PostMapping("/{shipmentId}/update-location-extapi")
    public ResponseEntity<TrackingEvents> updateShipmentLocation(@PathVariable Long shipmentId) {
        TrackingEvents trackingEvent = shipmentTrackingService.updateShipmentLocationExtApi(shipmentId);
        return ResponseEntity.ok(trackingEvent);
    }
}
