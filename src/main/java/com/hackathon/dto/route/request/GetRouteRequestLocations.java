package com.hackathon.dto.route.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetRouteRequestLocations {
    private Origin origin;

    private Destination destination;
}
