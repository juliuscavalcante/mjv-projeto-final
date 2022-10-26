package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.domain.entities.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
}
