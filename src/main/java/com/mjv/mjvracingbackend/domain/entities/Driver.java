package com.mjv.mjvracingbackend.domain.entities;

import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;

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
}
