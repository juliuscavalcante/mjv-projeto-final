package com.mjv.mjvracingbackend.domain.entities;

import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serial;

@Entity
public class Driver extends Person {

    @Serial
    private static final long serialVersionUID = 5803130385852025791L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    protected Integer podiums;

    protected Integer grandPrixEntered;

    protected Integer worldChampionships;

    protected Integer highestGridPosition;

    public Driver() {
        super();
        addProfile(Profile.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPodiums() {
        return podiums;
    }

    public void setPodiums(Integer podiums) {
        this.podiums = podiums;
    }

    public Integer getGrandPrixEntered() {
        return grandPrixEntered;
    }

    public void setGrandPrixEntered(Integer grandPrixEntered) {
        this.grandPrixEntered = grandPrixEntered;
    }

    public Integer getWorldChampionships() {
        return worldChampionships;
    }

    public void setWorldChampionships(Integer worldChampionships) {
        this.worldChampionships = worldChampionships;
    }

    public Integer getHighestGridPosition() {
        return highestGridPosition;
    }

    public void setHighestGridPosition(Integer highestGridPosition) {
        this.highestGridPosition = highestGridPosition;
    }


}
