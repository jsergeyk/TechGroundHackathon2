package com.hackathon.dto.route.response;

import lombok.Data;

@Data
public class LatLng {
    private final double latitude;
    private final double longitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}