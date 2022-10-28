package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.model.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
