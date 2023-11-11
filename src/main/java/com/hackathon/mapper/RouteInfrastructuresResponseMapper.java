package com.hackathon.mapper;

import com.hackathon.dto.routeInfrastructure.RouteHelperInfrastructureResponseDto;
import com.hackathon.entity.RouteHelpInfrastructure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteInfrastructuresResponseMapper {
    RouteHelpInfrastructure toEntity(RouteHelperInfrastructureResponseDto routeInfrastructureResponseDto);

    RouteHelperInfrastructureResponseDto toDto(RouteHelpInfrastructure routeInfrastructure);
}
