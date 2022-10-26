package com.mjv.mjvracingbackend.domain.entities;

import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serial;

@Entity
public class Manager extends Person {

    @Serial
    private static final long serialVersionUID = 5027049144376172678L;

    public Manager() {
        super();
        addProfile(Profile.ADMIN);
    }

    public Manager(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.ADMIN);
    }
}
