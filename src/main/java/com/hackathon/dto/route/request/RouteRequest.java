package com.hackathon.dto.route.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteRequest {

    private Origin origin;

    private Destination destination;

    private String travelMode;

    private String routingPreference;

    private boolean computeAlternativeRoutes;

    private RouteModifiers routeModifiers;
}