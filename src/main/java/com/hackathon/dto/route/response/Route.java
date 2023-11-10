package com.hackathon.dto.route.response;

import lombok.Data;

@Data
class Route {

    private int distanceMeters;

    private String duration;

    private Polyline polyline;
}