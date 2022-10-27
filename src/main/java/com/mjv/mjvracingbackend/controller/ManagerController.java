package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.domain.dto.ManagerDTO;
import com.mjv.mjvracingbackend.domain.entities.Manager;
import com.mjv.mjvracingbackend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<ManagerDTO>> findAll() {
        List<Manager> managerList = managerService.findAll();
        List<ManagerDTO> managerDTOList = managerList.stream().
                map(manager -> new ManagerDTO(manager)).collect(Collectors.toList());
        return ResponseEntity.ok().body(managerDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ManagerDTO> findById(@PathVariable Long id) {
        Manager manager = this.managerService.findById(id);
        return ResponseEntity.ok().body(new ManagerDTO(manager));
    }

    @PostMapping
    public ResponseEntity<ManagerDTO> create(@Valid @RequestBody ManagerDTO managerDTO) {
        Manager manager = managerService.create(managerDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(manager.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ManagerDTO> update(@Valid @PathVariable Long id, @RequestBody ManagerDTO managerDTO) {
        Manager manager = managerService.update(id, managerDTO);
        return ResponseEntity.ok().body(new ManagerDTO(manager));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ManagerDTO> delete(@PathVariable Long id) {
        managerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
