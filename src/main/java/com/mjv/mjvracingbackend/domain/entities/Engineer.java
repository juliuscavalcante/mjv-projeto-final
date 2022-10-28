package com.mjv.mjvracingbackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjv.mjvracingbackend.domain.dto.EngineerDTO;
import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Engineer(EngineerDTO engineerDTO) {
        this.id = engineerDTO.getId();
        this.name = engineerDTO.getName();
        this.cpf = engineerDTO.getCpf();
        this.email = engineerDTO.getEmail();
        this.password = engineerDTO.getPassword();
        this.profiles = engineerDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.birthDate = engineerDTO.getBirthDate();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
