package com.mjv.mjvracingbackend.domain.entities;

import com.mjv.mjvracingbackend.domain.dto.ManagerDTO;
import com.mjv.mjvracingbackend.domain.enums.Profile;
import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDate;
import java.util.stream.Collectors;

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

    public Manager(ManagerDTO managerDTO) {
        this.id = managerDTO.getId();
        this.name = managerDTO.getName();
        this.cpf = managerDTO.getCpf();
        this.email = managerDTO.getEmail();
        this.password = managerDTO.getPassword();
        this.profiles = managerDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.birthDate = managerDTO.getBirthDate();
    }
}
