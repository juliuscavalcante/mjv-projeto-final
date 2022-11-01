package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.model.entities.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {

    Optional<Engineer> findByCpf(String cpf);
    Optional<Engineer> findByEmail(String email);
}
