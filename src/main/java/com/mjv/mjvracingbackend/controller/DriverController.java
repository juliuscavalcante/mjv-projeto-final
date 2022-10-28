package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.model.dto.DriverDTO;
import com.mjv.mjvracingbackend.model.entities.Driver;
import com.mjv.mjvracingbackend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverDTO>> findAll() {
        List<Driver> driverList = driverService.findAll();
        List<DriverDTO> driverDTOList = driverList.stream().
                map(driver -> new DriverDTO(driver)).collect(Collectors.toList());
        return ResponseEntity.ok().body(driverDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DriverDTO> findById(@PathVariable Long id) {
        Driver driver = this.driverService.findById(id);
        return ResponseEntity.ok().body(new DriverDTO(driver));
    }

    @PostMapping
    public ResponseEntity<DriverDTO> create(@Valid @RequestBody DriverDTO driverDTO) {
        Driver driver = driverService.create(driverDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DriverDTO> update(@Valid @PathVariable Long id, @RequestBody DriverDTO driverDTO) {
        Driver driver = driverService.update(id, driverDTO);
        return ResponseEntity.ok().body(new DriverDTO(driver));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DriverDTO> delete(@PathVariable Long id) {
        driverService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
