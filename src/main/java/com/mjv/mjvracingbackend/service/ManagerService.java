package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.ManagerDTO;
import com.mjv.mjvracingbackend.model.entities.Manager;
import com.mjv.mjvracingbackend.repository.ManagerRepository;
import com.mjv.mjvracingbackend.repository.PersonRepository;
import com.mjv.mjvracingbackend.service.exception.DataIntegrityViolationException;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Manager findById(Long id) {
        Optional<Manager> managerOptional = managerRepository.findById(id);
        return managerOptional.orElseThrow(() -> new ObjectNotFoundException("manager id " + id + " not found"));
    }

    public Manager create(ManagerDTO managerDTO) {
        managerDTO.setId(null);
        managerDTO.setPassword(encoder.encode(managerDTO.getPassword()));
        validateCpfAndEmail(managerDTO);
        Manager manager = new Manager(managerDTO);
        return managerRepository.save(manager);
    }

    public Manager update(Long id, @Valid ManagerDTO managerDTO) {
        managerDTO.setId(id);
        Manager oldManager = findById(id);
        if (!managerDTO.getPassword().equals(oldManager.getPassword()))
            managerDTO.setPassword(encoder.encode(managerDTO.getPassword()));
        validateCpfAndEmail(managerDTO);
        oldManager = new Manager(managerDTO);
        return managerRepository.save(oldManager);
    }

    public void delete(Long id) {
        Manager manager = findById(id);
        managerRepository.deleteById(id);
    }

    private void validateCpfAndEmail(ManagerDTO managerDTO) {
        Optional<Manager> managerOptional = managerRepository.findByCpf(managerDTO.getCpf());
        if (managerOptional.isPresent() && managerOptional.get().getId() != managerDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        managerOptional = managerRepository.findByEmail(managerDTO.getEmail());
        if (managerOptional.isPresent() && managerOptional.get().getId() != managerDTO.getId()) {
            throw new DataIntegrityViolationException("This email is already registered in our system");
        }
    }
}
