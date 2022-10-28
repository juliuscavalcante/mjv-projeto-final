package com.mjv.mjvracingbackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjv.mjvracingbackend.domain.dto.MechanicDTO;
import com.mjv.mjvracingbackend.domain.enums.Profile;
import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Mechanic extends Person {

    @Serial
    private static final long serialVersionUID = -5682011832676031300L;

    @JsonIgnore
    @OneToMany(mappedBy = "mechanic")
    private List<Request> requests = new ArrayList<>();

    public Mechanic() {
        super();
        addProfile(Profile.USER);
    }

    public Mechanic(Long id, String name, String cpf, String email, String password, LocalDate birthDate) {
        super(id, name, cpf, email, password, birthDate);
    }

    public Mechanic(MechanicDTO mechanicDTO) {
        this.id = mechanicDTO.getId();
        this.name = mechanicDTO.getName();
        this.cpf = mechanicDTO.getCpf();
        this.email = mechanicDTO.getEmail();
        this.password = mechanicDTO.getPassword();
        this.profiles = mechanicDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.birthDate = mechanicDTO.getBirthDate();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
