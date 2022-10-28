package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.domain.dto.RequestDTO;
import com.mjv.mjvracingbackend.domain.entities.Request;
import com.mjv.mjvracingbackend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
