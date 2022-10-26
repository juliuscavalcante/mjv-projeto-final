package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.domain.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
