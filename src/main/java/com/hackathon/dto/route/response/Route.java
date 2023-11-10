package com.hackathon.dto.route.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    private int distanceMeters;

    private String duration;

    private Polyline polyline;
}