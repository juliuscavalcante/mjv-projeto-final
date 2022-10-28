package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.model.dto.MechanicDTO;
import com.mjv.mjvracingbackend.model.entities.Mechanic;
import com.mjv.mjvracingbackend.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/mechanic")
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;

    @GetMapping
    public ResponseEntity<List<MechanicDTO>> findAll() {
        List<Mechanic> mechanicList = mechanicService.findAll();
        List<MechanicDTO> mechanicDTOList = mechanicList.stream().
                map(mechanic -> new MechanicDTO(mechanic)).collect(Collectors.toList());
        return ResponseEntity.ok().body(mechanicDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MechanicDTO> findById(@PathVariable Long id) {
        Mechanic mechanic = this.mechanicService.findById(id);
        return ResponseEntity.ok().body(new MechanicDTO(mechanic));
    }

    @PostMapping
    public ResponseEntity<MechanicDTO> create(@Valid @RequestBody MechanicDTO mechanicDTO) {
        Mechanic mechanic = mechanicService.create(mechanicDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(mechanic.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MechanicDTO> update(@Valid @PathVariable Long id, @RequestBody MechanicDTO mechanicDTO) {
        Mechanic mechanic = mechanicService.update(id, mechanicDTO);
        return ResponseEntity.ok().body(new MechanicDTO(mechanic));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MechanicDTO> delete(@PathVariable Long id) {
        mechanicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
