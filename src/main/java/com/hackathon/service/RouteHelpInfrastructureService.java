package com.hackathon.service;

import com.hackathon.dto.routeInfrastructure.RouteHelperInfrastructureResponseDto;
import com.hackathon.dto.routeInfrastructure.RouteHelperInfrastructureSavingDto;
import com.hackathon.entity.RouteHelpInfrastructure;
import com.hackathon.mapper.RouteInfrastructuresResponseMapper;
import com.hackathon.mapper.RouteInfrastructuresSavingMapper;
import com.hackathon.persistence.repositories.RouteHelpInfrastructureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteHelpInfrastructureService {
    private final RouteHelpInfrastructureRepository routeHelpInfrastructureRepository;
    private final RouteInfrastructuresResponseMapper routeInfrastructuresResponseMapper;
    private final RouteInfrastructuresSavingMapper routeInfrastructuresSavingMapper;

    @Transactional
    public void saveAll(Collection<RouteHelpInfrastructure> routeHelpInfrastructures) {
        routeHelpInfrastructureRepository.saveAll(routeHelpInfrastructures);
    }

    @Transactional(readOnly = true)
    public Page<RouteHelperInfrastructureResponseDto> getAllRouteInfrastructures(Pageable pageable) {
        return routeHelpInfrastructureRepository.findAll(pageable)
                .map(routeInfrastructuresResponseMapper::toDto);
    }


    @Transactional(readOnly = true)
    public RouteHelperInfrastructureResponseDto getRouteInfrastructures(Long id) {
        return routeHelpInfrastructureRepository.findById(id).map(routeInfrastructuresResponseMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id %s not found", id)));
    }

    @Transactional
    public RouteHelperInfrastructureResponseDto saveRouteInfrastructures
            (RouteHelperInfrastructureSavingDto routeInfrastructureSavingDto) {
        RouteHelpInfrastructure routeHelpInfrastructure = routeInfrastructuresSavingMapper.toEntity(routeInfrastructureSavingDto);
        RouteHelpInfrastructure savedRouteHelpInfrastructure = routeHelpInfrastructureRepository.save(routeHelpInfrastructure);
        return routeInfrastructuresResponseMapper.toDto(savedRouteHelpInfrastructure);
    }

    @Transactional
    public RouteHelperInfrastructureResponseDto updateRouteInfrastructuresById
            (Long id, RouteHelperInfrastructureSavingDto routeInfrastructureSavingDto) {

        RouteHelpInfrastructure routeInfrastructure = routeHelpInfrastructureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity with id %s not found", id)));
        Optional.ofNullable(routeInfrastructureSavingDto.getDescription())
                .ifPresent(routeInfrastructure::setDescription);
        Optional.ofNullable(routeInfrastructureSavingDto.getName())
                .ifPresent(routeInfrastructure::setName);
        Optional.ofNullable(routeInfrastructureSavingDto.getLatitude())
                .ifPresent(routeInfrastructure::setLatitude);
        Optional.ofNullable(routeInfrastructureSavingDto.getLongitude())
                .ifPresent(routeInfrastructure::setLongitude);

        return routeInfrastructuresResponseMapper.toDto(routeInfrastructure);
    }
}

