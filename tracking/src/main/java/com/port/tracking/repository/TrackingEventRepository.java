package com.port.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.port.tracking.model.TrackingEvents;

public interface TrackingEventRepository extends JpaRepository<TrackingEvents, Long> {
    List<TrackingEvents> findByTrackingNumberOrderByTimestampAsc(String trackingNumber);
}
