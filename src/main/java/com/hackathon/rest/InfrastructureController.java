package com.hackathon.rest;

import com.hackathon.dto.InfrastructureDTO;
import com.hackathon.entity.Infrastructure;
import com.hackathon.service.InfrastructureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InfrastructureController {

    private final Logger logger = LogManager.getLogger(InfrastructureController.class);
    private final InfrastructureService infrastructureService;

    public InfrastructureController(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    @GetMapping (value = "/infrastructures")
    public ResponseEntity<List<InfrastructureDTO>> getAll() {
        List<Infrastructure> infrastructures = infrastructureService.findAll();
        List<InfrastructureDTO> infrastructureDTOs = infrastructures.stream()
                .map(i -> new InfrastructureDTO(i.getName(), i.getAvailability(), i.getLatitude(), i.getLongitude()))
                .toList();
        logger.info("GET /api/v1/infrastructures: " + infrastructureDTOs.size());
        return ResponseEntity.ok().body(infrastructureDTOs);
    }
}