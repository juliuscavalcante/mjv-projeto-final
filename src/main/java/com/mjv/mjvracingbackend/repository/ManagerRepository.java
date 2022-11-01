package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.model.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByCpf(String cpf);
    Optional<Manager> findByEmail(String email);
}
