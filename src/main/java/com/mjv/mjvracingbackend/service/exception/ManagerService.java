package com.mjv.mjvracingbackend.service.exception;

import com.mjv.mjvracingbackend.domain.dto.ManagerDTO;
import com.mjv.mjvracingbackend.domain.entities.Manager;
import com.mjv.mjvracingbackend.domain.entities.Person;
import com.mjv.mjvracingbackend.repository.ManagerRepository;
import com.mjv.mjvracingbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Manager findById(Long id) {
        Optional<Manager> managerOptional = managerRepository.findById(id);
        return managerOptional.orElseThrow(() -> new ObjectNotFoundException("manager id " + id + " not found"));
    }

    public Manager create(ManagerDTO managerDTO) {
        managerDTO.setId(null);
        validateCpfAndEmail(managerDTO);
        Manager manager = new Manager(managerDTO);
        return managerRepository.save(manager);
    }

    public Manager update(Long id, @Valid ManagerDTO managerDTO) {
        managerDTO.setId(id);
        Manager manager = findById(id);
        validateCpfAndEmail(managerDTO);
        manager = new Manager(managerDTO);
        return managerRepository.save(manager);
    }

    public void delete(Long id) {
        Manager manager = findById(id);
        managerRepository.deleteById(id);
    }

    private void validateCpfAndEmail(ManagerDTO managerDTO) {
        Optional<Person> managerOptional = personRepository.findByCpf(managerDTO.getCpf());
        if (managerOptional.isPresent() && managerOptional.get().getId() != managerDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        managerOptional = personRepository.findByEmail(managerDTO.getEmail());
        if (managerOptional.isPresent() && managerOptional.get().getId() != managerDTO.getId()) {
            throw new DataIntegrityViolationException("This email is already registered in our system");
        }
    }
}
