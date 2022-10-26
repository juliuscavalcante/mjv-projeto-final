package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.domain.dto.DriverDTO;
import com.mjv.mjvracingbackend.domain.entities.Driver;
import com.mjv.mjvracingbackend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DriverDTO> findById(@PathVariable Long id) {
        Driver driver = this.driverService.findById(id);
        return ResponseEntity.ok().body(new DriverDTO(driver));
    }
}
