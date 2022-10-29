package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.model.dto.RequestDTO;
import com.mjv.mjvracingbackend.model.entities.Request;
import com.mjv.mjvracingbackend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RequestDTO> findById(@PathVariable Long id) {
        Request request = requestService.findById(id);
        return ResponseEntity.ok().body(new RequestDTO(request));
    }

    @GetMapping
    public ResponseEntity<List<RequestDTO>> findAll() {
        List<Request> requestList = requestService.findAll();
        List<RequestDTO> requestDTOList = requestList.stream()
                .map(request -> new RequestDTO(request)).collect(Collectors.toList());
        return ResponseEntity.ok().body(requestDTOList);
    }

    @PostMapping
    public ResponseEntity<RequestDTO> create(@Valid @RequestBody RequestDTO requestDTO) {
        Request request = requestService.create(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RequestDTO> update(@PathVariable Long id, @Valid @RequestBody RequestDTO requestDTO) {
        Request request = requestService.update(id, requestDTO);
        return ResponseEntity.ok().body(new RequestDTO(request));
    }
}
