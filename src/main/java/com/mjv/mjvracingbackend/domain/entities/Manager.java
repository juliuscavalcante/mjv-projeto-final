package com.mjv.mjvracingbackend.domain.entities;

import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serial;

@Entity
public class Manager extends Person {

    @Serial
    private static final long serialVersionUID = 5027049144376172678L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Manager() {
        super();
        addProfile(Profile.ADMIN);
    }
}
