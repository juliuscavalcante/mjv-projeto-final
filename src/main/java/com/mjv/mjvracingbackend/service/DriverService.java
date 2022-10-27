package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.domain.dto.DriverDTO;
import com.mjv.mjvracingbackend.domain.entities.Driver;
import com.mjv.mjvracingbackend.domain.entities.Person;
import com.mjv.mjvracingbackend.repository.DriverRepository;
import com.mjv.mjvracingbackend.repository.PersonRepository;
import com.mjv.mjvracingbackend.service.exception.DataIntegrityViolationException;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findById(Long id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        return driverOptional.orElseThrow(() -> new ObjectNotFoundException("driver id " + id + " not found"));
    }

    public Driver create(DriverDTO driverDTO) {
        driverDTO.setId(null);
        validateCpfAndEmail(driverDTO);
        Driver driver = new Driver(driverDTO);
        return driverRepository.save(driver);
    }

    public Driver update(Long id, @Valid DriverDTO driverDTO) {
        driverDTO.setId(id);
        Driver driver = findById(id);
        validateCpfAndEmail(driverDTO);
        driver = new Driver(driverDTO);
        return driverRepository.save(driver);
    }

    public void delete(Long id) {
        Driver driver = findById(id);
        driverRepository.deleteById(id);
    }

    private void validateCpfAndEmail(DriverDTO driverDTO) {
        Optional<Person> driverOptional = personRepository.findByCpf(driverDTO.getCpf());
        if (driverOptional.isPresent() && driverOptional.get().getId() != driverDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        driverOptional = personRepository.findByEmail(driverDTO.getEmail());
        if (driverOptional.isPresent() && driverOptional.get().getId() != driverDTO.getId()) {
            throw new DataIntegrityViolationException("This email is already registered in our system");
        }
    }
}
