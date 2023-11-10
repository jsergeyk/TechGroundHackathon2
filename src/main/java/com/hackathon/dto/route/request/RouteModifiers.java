package com.hackathon.dto.route.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteModifiers {

    private boolean avoidTolls;

    private boolean avoidHighways;

    private boolean avoidFerries;

}