package com.hackathon.dto.route.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetRouteRequestLocations {
    private Origin origin;

    private Destination destination;

    private List<Intermediate> intermediates;
}
