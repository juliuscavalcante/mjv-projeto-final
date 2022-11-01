package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.model.entities.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

    Optional<Mechanic> findByCpf(String cpf);
    Optional<Mechanic> findByEmail(String email);
}
