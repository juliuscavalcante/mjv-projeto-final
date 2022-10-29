package com.mjv.mjvracingbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mjv.mjvracingbackend.model.entities.Request;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class RequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6451544217549972562L;

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;

    @NotNull(message = "priority is required")
    private Integer priority;

    @NotNull(message = "status is required")
    private Integer status;

    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "notes is required")
    private String notes;

    @NotNull(message = "engineer is required")
    private Long engineer;

    @NotNull(message = "mechanic is required")
    private Long mechanic;

    private String engineerName;

    private String mechanicrName;

    public RequestDTO() {
        super();
    }

    public RequestDTO(Request request) {
        this.id = request.getId();
        this.openingDate = request.getOpeningDate();
        this.closingDate = request.getClosingDate();
        this.priority = request.getPriority().getCode();
        this.status = request.getStatus().getCode();
        this.title = request.getTitle();
        this.notes = request.getNotes();
        this.engineer = request.getEngineer().getId();
        this.mechanic = request.getMechanic().getId();
        this.engineerName = request.getEngineer().getName();
        this.mechanicrName = request.getMechanic().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getEngineer() {
        return engineer;
    }

    public void setEngineer(Long engineer) {
        this.engineer = engineer;
    }

    public Long getMechanic() {
        return mechanic;
    }

    public void setMechanic(Long mechanic) {
        this.mechanic = mechanic;
    }

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }

    public String getMechanicrName() {
        return mechanicrName;
    }

    public void setMechanicrName(String mechanicrName) {
        this.mechanicrName = mechanicrName;
    }
}
