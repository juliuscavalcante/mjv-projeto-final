package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.model.dto.EngineerDTO;
import com.mjv.mjvracingbackend.model.entities.Engineer;
import com.mjv.mjvracingbackend.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/engineer")
public class EngineerController {

    @Autowired
    private EngineerService engineerService;

    @GetMapping
    public ResponseEntity<List<EngineerDTO>> findAll() {
        List<Engineer> engineerList = engineerService.findAll();
        List<EngineerDTO> engineerDTOList = engineerList.stream().
                map(engineer -> new EngineerDTO(engineer)).collect(Collectors.toList());
        return ResponseEntity.ok().body(engineerDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EngineerDTO> findById(@PathVariable Long id) {
        Engineer engineer = this.engineerService.findById(id);
        return ResponseEntity.ok().body(new EngineerDTO(engineer));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EngineerDTO> create(@Valid @RequestBody EngineerDTO engineerDTO) {
        Engineer engineer = engineerService.create(engineerDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(engineer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EngineerDTO> update(@Valid @PathVariable Long id, @RequestBody EngineerDTO engineerDTO) {
        Engineer engineer = engineerService.update(id, engineerDTO);
        return ResponseEntity.ok().body(new EngineerDTO(engineer));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EngineerDTO> delete(@PathVariable Long id) {
        engineerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
