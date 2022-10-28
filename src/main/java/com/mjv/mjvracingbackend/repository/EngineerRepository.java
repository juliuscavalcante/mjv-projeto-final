package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.model.entities.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {
}
