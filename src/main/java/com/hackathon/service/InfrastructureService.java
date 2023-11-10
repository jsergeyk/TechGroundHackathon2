package com.hackathon.service;

import com.hackathon.entity.Infrastructure;
import com.hackathon.persistence.repositories.InfrastructureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfrastructureService {

    private final InfrastructureRepository infrastructureRepository;

    public InfrastructureService(InfrastructureRepository infrastructureRepository) {
        this.infrastructureRepository = infrastructureRepository;
    }

    public List<Infrastructure> findAll() {
        return infrastructureRepository.findAll();
    }
}
