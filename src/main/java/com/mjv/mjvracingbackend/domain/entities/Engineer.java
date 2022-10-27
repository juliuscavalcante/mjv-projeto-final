package com.mjv.mjvracingbackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjv.mjvracingbackend.domain.enums.Profile;
import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Engineer extends Person {

    @Serial
    private static final long serialVersionUID = -5836532822531249712L;

    @JsonIgnore
    @OneToMany(mappedBy = "engineer")
    private List<Request> requests = new ArrayList<>();


    public Engineer() {
        super();
        addProfile(Profile.USER);
    }

    public Engineer(Long id, String name, String cpf, String email, String password, LocalDate birthDate) {
        super(id, name, cpf, email, password, birthDate);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
