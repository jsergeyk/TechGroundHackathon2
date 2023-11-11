package com.hackathon.rest;

import com.hackathon.dto.routeInfrastructure.RouteHelperInfrastructureResponseDto;
import com.hackathon.dto.routeInfrastructure.RouteHelperInfrastructureSavingDto;
import com.hackathon.service.RouteHelpInfrastructureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/route-help-infrastructures")
public class RouteHelpInfrastructureRestController {
    private final RouteHelpInfrastructureService routeHelpInfrastructureService;

    @GetMapping
    public ResponseEntity<Page<RouteHelperInfrastructureResponseDto>> getAllRouteInfrastructures(@RequestParam(required = false, defaultValue = "0") int page,
                                                                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                                                                 @RequestParam(required = false, defaultValue = "id") String sort) {
        Page<RouteHelperInfrastructureResponseDto> allRouteInfrastructures = routeHelpInfrastructureService.
                getAllRouteInfrastructures(PageRequest.of(page, size, Sort.by(sort)));
        return ResponseEntity.ok(allRouteInfrastructures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteHelperInfrastructureResponseDto> getRouteInfrastructures(@PathVariable Long id) {
        return ResponseEntity.ok(routeHelpInfrastructureService
                .getRouteInfrastructures(id));
    }

    @PostMapping
    public ResponseEntity<RouteHelperInfrastructureResponseDto> saveRouteInfrastructures
            (@RequestBody @Valid RouteHelperInfrastructureSavingDto routeHelperInfrastructureSavingDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        RouteHelperInfrastructureResponseDto routeHelperInfrastructureResponseDto = routeHelpInfrastructureService
                .saveRouteInfrastructures(routeHelperInfrastructureSavingDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(routeHelperInfrastructureResponseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(routeHelperInfrastructureResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RouteHelperInfrastructureResponseDto> updateRouteInfrastructures
            (@RequestBody RouteHelperInfrastructureSavingDto routeInfrastructureSavingDto,
             @PathVariable Long id) {
        RouteHelperInfrastructureResponseDto routeInfrastructureResponseDto = routeHelpInfrastructureService
                .updateRouteInfrastructuresById(id, routeInfrastructureSavingDto);
        return ResponseEntity.ok(routeInfrastructureResponseDto);
    }
}
