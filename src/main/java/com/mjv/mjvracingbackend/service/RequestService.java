package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.domain.entities.Request;
import com.mjv.mjvracingbackend.repository.EngineerRepository;
import com.mjv.mjvracingbackend.repository.MechanicRepository;
import com.mjv.mjvracingbackend.repository.RequestRepository;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Autowired
    private MechanicRepository mechanicRepository;


    public Request findById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        return requestOptional.orElseThrow(() -> new ObjectNotFoundException("Request id " + id + " not found"));
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

}
