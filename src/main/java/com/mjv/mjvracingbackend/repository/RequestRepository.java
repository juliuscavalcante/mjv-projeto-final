package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.domain.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
