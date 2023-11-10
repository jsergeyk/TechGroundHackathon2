package com.hackathon.dto.route.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationWrapper {
    private LatLngWrapper latLng;
}