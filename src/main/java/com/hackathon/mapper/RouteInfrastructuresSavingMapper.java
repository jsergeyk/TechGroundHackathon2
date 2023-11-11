package com.hackathon.mapper;

import com.hackathon.dto.routeInfrastructure.RouteHelperInfrastructureSavingDto;
import com.hackathon.entity.RouteHelpInfrastructure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteInfrastructuresSavingMapper {
    RouteHelpInfrastructure toEntity(RouteHelperInfrastructureSavingDto routeHelperInfrastructureSavingDto);

    RouteHelperInfrastructureSavingDto toDto(RouteHelpInfrastructure routeInfrastructure);
}
