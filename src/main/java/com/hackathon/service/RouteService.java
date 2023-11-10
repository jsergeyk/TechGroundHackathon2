package com.hackathon.service;

import com.hackathon.decoder.PolyLineDecoder;
import com.hackathon.dto.route.request.GetRouteRequestLocations;
import com.hackathon.dto.route.request.RouteModifiers;
import com.hackathon.dto.route.request.RouteRequest;
import com.hackathon.dto.route.response.LatLng;
import com.hackathon.dto.route.response.RouteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RestTemplate restTemplate;
    @Value("${google-maps.api.key}")
    private String googleMapsApiKey;

    public List<LatLng> getRoutePolyline(GetRouteRequestLocations getRouteRequestLocations) {
        RouteRequest routeRequest = buildRouteRequest(getRouteRequestLocations);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Goog-FieldMask", "routes.duration,routes.distanceMeters,routes.polyline.encodedPolyline");
        headers.set("X-Goog-Api-Key", googleMapsApiKey);

        HttpEntity<RouteRequest> requestEntity = new HttpEntity<>(routeRequest, headers);

        ResponseEntity<RouteDTO> responseEntity = restTemplate
                .postForEntity("https://routes.googleapis.com/directions/v2:computeRoutes", requestEntity, RouteDTO.class);
        return PolyLineDecoder.decodePolyline(responseEntity.getBody().getRoutes().get(0).getPolyline().getEncodedPolyline());
    }

    private RouteRequest buildRouteRequest(GetRouteRequestLocations getRouteRequestLocations) {
        return RouteRequest.builder()
                .origin(getRouteRequestLocations.getOrigin())
                .destination(getRouteRequestLocations.getDestination())
                .travelMode("WALK")
                .routingPreference("ROUTING_PREFERENCE_UNSPECIFIED")
                .computeAlternativeRoutes(true)
                .routeModifiers(buildRouteModifiers())
                .build();
    }

    private RouteModifiers buildRouteModifiers() {
        return RouteModifiers.builder()
                .avoidFerries(false)
                .avoidHighways(false)
                .avoidTolls(false)
                .build();
    }

}
