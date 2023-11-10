package com.hackathon.persistence.repositories;

import com.hackathon.entity.Infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure, Long> {

}
