package com.port.tracking.extapi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.port.tracking.dto.ShipmentLocationDto;

@Service
public class ShipmentGpsService {

    public ShipmentLocationDto getGpsLocation(String containerId) {
        RestTemplate restTemplate = new RestTemplate();
        String gpsApiUrl = "https://external-gps-service.com/api/location/{containerId}";
        ShipmentLocationDto locationDto = restTemplate.getForObject(gpsApiUrl, ShipmentLocationDto.class, containerId);

        return locationDto;
    }
}
