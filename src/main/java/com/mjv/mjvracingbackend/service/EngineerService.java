package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.EngineerDTO;
import com.mjv.mjvracingbackend.model.entities.Engineer;
import com.mjv.mjvracingbackend.model.entities.Person;
import com.mjv.mjvracingbackend.repository.EngineerRepository;
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
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Engineer> findAll() {
        return engineerRepository.findAll();
    }

    public Engineer findById(Long id) {
        Optional<Engineer> engineerOptional = engineerRepository.findById(id);
        return engineerOptional.orElseThrow(() -> new ObjectNotFoundException("engineer id " + id + " not found"));
    }

    public Engineer create(EngineerDTO engineerDTO) {
        engineerDTO.setId(null);
        engineerDTO.setPassword(encoder.encode(engineerDTO.getPassword()));
        validateCpfAndEmail(engineerDTO);
        Engineer engineer = new Engineer(engineerDTO);
        return engineerRepository.save(engineer);
    }

    public Engineer update(Long id, @Valid EngineerDTO engineerDTO) {
        engineerDTO.setId(id);
        Engineer oldEngineer = findById(id);
        if (!engineerDTO.getPassword().equals(oldEngineer.getPassword()))
            engineerDTO.setPassword(encoder.encode(engineerDTO.getPassword()));
        validateCpfAndEmail(engineerDTO);
        oldEngineer = new Engineer(engineerDTO);
        return engineerRepository.save(oldEngineer);
    }

    public void delete(Long id) {
        Engineer engineer = findById(id);
        if (engineer.getRequests().size() > 0) {
            throw new DataIntegrityViolationException("The Engineer has one or more Service Orders, and it is not possible to delete him");
        }
        engineerRepository.deleteById(id);
    }

    private void validateCpfAndEmail(EngineerDTO engineerDTO) {
        Optional<Person> engineerOptional = personRepository.findByCpf(engineerDTO.getCpf());
        if (engineerOptional.isPresent() && engineerOptional.get().getId() != engineerDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        engineerOptional = personRepository.findByEmail(engineerDTO.getEmail());
        if (engineerOptional.isPresent() && engineerOptional.get().getId() != engineerDTO.getId()) {
            throw new DataIntegrityViolationException("This email is already registered in our system");
        }
    }
}
