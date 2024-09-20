package com.port.tracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.port.tracking.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
