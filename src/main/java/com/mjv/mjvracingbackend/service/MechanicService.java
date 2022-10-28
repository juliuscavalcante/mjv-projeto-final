package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.domain.dto.MechanicDTO;
import com.mjv.mjvracingbackend.domain.entities.Mechanic;
import com.mjv.mjvracingbackend.domain.entities.Person;
import com.mjv.mjvracingbackend.repository.MechanicRepository;
import com.mjv.mjvracingbackend.repository.PersonRepository;
import com.mjv.mjvracingbackend.service.exception.DataIntegrityViolationException;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class MechanicService {

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Mechanic> findAll() {
        return mechanicRepository.findAll();
    }

    public Mechanic findById(Long id) {
        Optional<Mechanic> mechanicOptional = mechanicRepository.findById(id);
        return mechanicOptional.orElseThrow(() -> new ObjectNotFoundException("mechanic id " + id + " not found"));
    }

    public Mechanic create(MechanicDTO mechanicDTO) {
        mechanicDTO.setId(null);
        validateCpfAndEmail(mechanicDTO);
        Mechanic mechanic = new Mechanic(mechanicDTO);
        return mechanicRepository.save(mechanic);
    }

    public Mechanic update(Long id, @Valid MechanicDTO mechanicDTO) {
        mechanicDTO.setId(id);
        Mechanic mechanic = findById(id);
        validateCpfAndEmail(mechanicDTO);
        mechanic = new Mechanic(mechanicDTO);
        return mechanicRepository.save(mechanic);
    }

    public void delete(Long id) {
        Mechanic mechanic = findById(id);
        mechanicRepository.deleteById(id);
    }

    private void validateCpfAndEmail(MechanicDTO mechanicDTO) {
        Optional<Person> mechanicOptional = personRepository.findByCpf(mechanicDTO.getCpf());
        if (mechanicOptional.isPresent() && mechanicOptional.get().getId() != mechanicDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        mechanicOptional = personRepository.findByEmail(mechanicDTO.getEmail());
        if (mechanicOptional.isPresent() && mechanicOptional.get().getId() != mechanicDTO.getId()) {
            throw new DataIntegrityViolationException("This email is already registered in our system");
        }
    }
}
