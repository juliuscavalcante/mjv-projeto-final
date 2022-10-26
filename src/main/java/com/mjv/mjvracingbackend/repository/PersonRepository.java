package com.mjv.mjvracingbackend.repository;

import com.mjv.mjvracingbackend.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByEmail(String email);
}
