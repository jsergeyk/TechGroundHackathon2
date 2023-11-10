package com.hackathon.rest;

import com.hackathon.dto.route.request.GetRouteRequestLocations;
import com.hackathon.dto.route.response.LatLng;
import com.hackathon.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/route")
public class RouteRestController {
    private final RouteService routeService;

    @PostMapping("get-polyline")
    public ResponseEntity<List<LatLng>> getRoutePolyline(@RequestBody GetRouteRequestLocations getRouteRequestLocations) {
        return ResponseEntity.ok(routeService.getRoutePolyline(getRouteRequestLocations));
    }
}
