package com.mjv.mjvracingbackend.controller;

import com.mjv.mjvracingbackend.domain.dto.RequestDTO;
import com.mjv.mjvracingbackend.domain.entities.Request;
import com.mjv.mjvracingbackend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
