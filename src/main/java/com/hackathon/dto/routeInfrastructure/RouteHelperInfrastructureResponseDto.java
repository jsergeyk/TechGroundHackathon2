package com.hackathon.dto.routeInfrastructure;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RouteHelperInfrastructureResponseDto {
    private Long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
}
