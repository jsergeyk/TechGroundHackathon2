package com.hackathon.service;

import com.hackathon.dto.route.request.GetRouteRequestLocations;
import com.hackathon.dto.route.request.RouteModifiers;
import com.hackathon.dto.route.request.RouteRequest;
import com.hackathon.dto.route.response.RouteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RestTemplate restTemplate;

    public RouteDTO getRoutePolyline(GetRouteRequestLocations getRouteRequestLocations) {
        RouteRequest routeRequest = buildRouteRequest(getRouteRequestLocations);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Goog-FieldMask", "routes.duration,routes.distanceMeters,routes.polyline.encodedPolyline");
        headers.set("X-Goog-Api-Key", "AIzaSyAb6LpS2kUgaB4FRL1-kQyeexf9a06eLvg"); //todo винести в проперті цей апі кей

        HttpEntity<RouteRequest> requestEntity = new HttpEntity<>(routeRequest, headers);

        ResponseEntity<RouteDTO> responseEntity = restTemplate
                .postForEntity("https://routes.googleapis.com/directions/v2:computeRoutes", requestEntity, RouteDTO.class);

        return responseEntity.getBody();
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
