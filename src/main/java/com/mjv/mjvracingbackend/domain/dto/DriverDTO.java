package com.mjv.mjvracingbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mjv.mjvracingbackend.domain.entities.Driver;
import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DriverDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3304047795305776058L;

    protected Long id;

    @NotNull(message = "name is required")
    protected String  name;

    @NotNull(message = "CPF is required")
    protected String cpf;

    @NotNull(message = "email is required")
    protected String email;

    @NotNull(message = "password is required")
    protected String password;

    protected Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate birthDate = LocalDate.now();

    public DriverDTO(Driver driver) {
        this.id = driver.getId();
        this.name = driver.getName();
        this.cpf = driver.getCpf();
        this.email = driver.getEmail();
        this.password = driver.getPassword();
        this.profiles = driver.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.birthDate = driver.getBirthDate();
    }

    public DriverDTO() {
        super();
        addProfile(Profile.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profiles) {
        this.profiles.add(profiles.getCode());
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
