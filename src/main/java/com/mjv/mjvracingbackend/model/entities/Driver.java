package com.mjv.mjvracingbackend.model.entities;

import com.mjv.mjvracingbackend.model.dto.DriverDTO;
import com.mjv.mjvracingbackend.model.enums.Profile;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Entity
public class Driver extends Person {

    @Serial
    private static final long serialVersionUID = 5803130385852025791L;

    public Driver() {
        super();
        addProfile(Profile.USER);
    }

    public Driver(Long id, String name, String cpf, String email, String password, LocalDate birthDate) {
        super(id, name, cpf, email, password, birthDate);
        addProfile(Profile.USER);
    }

    public Driver(DriverDTO driverDTO) {
        this.id = driverDTO.getId();
        this.name = driverDTO.getName();
        this.cpf = driverDTO.getCpf();
        this.email = driverDTO.getEmail();
        this.password = driverDTO.getPassword();
        this.profiles = driverDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.birthDate = driverDTO.getBirthDate();
    }
}
