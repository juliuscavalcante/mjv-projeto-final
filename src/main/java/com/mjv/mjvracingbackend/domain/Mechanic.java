package com.mjv.mjvracingbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjv.mjvracingbackend.domain.enums.Profile;
import javax.persistence.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mechanic extends Person {

    @Serial
    private static final long serialVersionUID = -5682011832676031300L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "mechanic")
    private List<Request> requests = new ArrayList<>();

    public Mechanic() {
        super();
        addProfile(Profile.USER);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
