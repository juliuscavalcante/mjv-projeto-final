package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.domain.entities.Driver;
import com.mjv.mjvracingbackend.repository.DriverRepository;
import com.mjv.mjvracingbackend.repository.PersonRepository;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PersonRepository personRepository;

    public Driver findById(Long id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        return driverOptional.orElseThrow(() -> new ObjectNotFoundException("driver id " + id + " not found"));
    }
}
