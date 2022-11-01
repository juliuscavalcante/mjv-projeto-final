package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.model.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByCpf(String cpf);
    Optional<Driver> findByEmail(String email);
}
