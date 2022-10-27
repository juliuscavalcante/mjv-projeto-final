package com.mjv.mjvracingbackend.domain.entities;

import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;

@Entity
public class Manager extends Person {

    @Serial
    private static final long serialVersionUID = 5027049144376172678L;

    public Manager() {
        super();
        addProfile(Profile.ADMIN);
    }

    public Manager(Long id, String name, String cpf, String email, String password, LocalDate birthDate) {
        super(id, name, cpf, email, password, birthDate);
    }
}
