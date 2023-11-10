package com.hackathon.dto.route.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LatLngWrapper {

    private double latitude;

    private double longitude;
}