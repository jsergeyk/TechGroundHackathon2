package com.hackathon.dto.routeInfrastructure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RouteHelperInfrastructureSavingDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
}
