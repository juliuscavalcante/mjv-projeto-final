package com.mjv.mjvracingbackend.domain.entities;

import com.mjv.mjvracingbackend.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serial;

@Entity
public class Driver extends Person {

    @Serial
    private static final long serialVersionUID = 5803130385852025791L;

    protected Integer podiums;

    protected Integer grandPrixEntered;

    protected Integer worldChampionships;

    protected Integer victories;

    public Driver() {
        super();
        addProfile(Profile.USER);
    }

    public Driver(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.USER);
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
        return victories;
    }

    public void setHighestGridPosition(Integer highestGridPosition) {
        this.victories = highestGridPosition;
    }


}
