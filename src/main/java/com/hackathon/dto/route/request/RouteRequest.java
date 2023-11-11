package com.hackathon.dto.route.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteRequest {

    private Origin origin;

    private Destination destination;

    private List<Intermediate> intermediates;

    private String travelMode;

    private String routingPreference;

    private boolean computeAlternativeRoutes;

    private RouteModifiers routeModifiers;
}