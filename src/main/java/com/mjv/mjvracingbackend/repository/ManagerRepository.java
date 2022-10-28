package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.model.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
