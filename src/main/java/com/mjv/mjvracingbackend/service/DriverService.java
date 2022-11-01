package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.DriverDTO;
import com.mjv.mjvracingbackend.model.entities.Driver;
import com.mjv.mjvracingbackend.repository.DriverRepository;
import com.mjv.mjvracingbackend.service.exception.DataIntegrityViolationException;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findById(Long id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        return driverOptional.orElseThrow(() -> new ObjectNotFoundException("driver id " + id + " not found"));
    }

    public Driver create(DriverDTO driverDTO) {
        driverDTO.setId(null);
        driverDTO.setPassword(encoder.encode(driverDTO.getPassword()));
        validateCpfAndEmail(driverDTO);
        Driver driver = new Driver(driverDTO);
        return driverRepository.save(driver);
    }

    public Driver update(Long id, @Valid DriverDTO driverDTO) {
        driverDTO.setId(id);
        Driver oldDriver = findById(id);
        if (!driverDTO.getPassword().equals(oldDriver.getPassword()))
            driverDTO.setPassword(encoder.encode(driverDTO.getPassword()));
        validateCpfAndEmail(driverDTO);
        oldDriver = new Driver(driverDTO);
        return driverRepository.save(oldDriver);
    }

    public void delete(Long id) {
        Driver driver = findById(id);
        driverRepository.deleteById(id);
    }

    private void validateCpfAndEmail(DriverDTO driverDTO) {
        Optional<Driver> driverOptional = driverRepository.findByCpf(driverDTO.getCpf());
        if (driverOptional.isPresent() && driverOptional.get().getId() != driverDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        driverOptional = driverRepository.findByEmail(driverDTO.getEmail());
        if (driverOptional.isPresent() && driverOptional.get().getId() != driverDTO.getId()) {
            throw new DataIntegrityViolationException("This email is already registered in our system");
        }
    }
}
