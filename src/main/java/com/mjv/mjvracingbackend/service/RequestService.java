package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.RequestDTO;
import com.mjv.mjvracingbackend.model.entities.Engineer;
import com.mjv.mjvracingbackend.model.entities.Mechanic;
import com.mjv.mjvracingbackend.model.entities.Request;
import com.mjv.mjvracingbackend.model.enums.Priority;
import com.mjv.mjvracingbackend.model.enums.Status;
import com.mjv.mjvracingbackend.repository.RequestRepository;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EngineerService engineerService;

    @Autowired
    private MechanicService mechanicService;


    public Request findById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        return requestOptional.orElseThrow(() -> new ObjectNotFoundException("Request id " + id + " not found"));
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request create(@Valid RequestDTO requestDTO) {
        return requestRepository.save(newRequest(requestDTO));
    }

    public Request update(Long id, @Valid RequestDTO requestDTO) {
        requestDTO.setId(id);
        Request request = findById(id);
        request = newRequest(requestDTO);
        return requestRepository.save(request);
    }

    private Request newRequest(RequestDTO requestDTO) {
        Engineer engineer = engineerService.findById(requestDTO.getEngineer());
        Mechanic mechanic = mechanicService.findById(requestDTO.getMechanic());

        Request request = new Request();
        if (requestDTO.getId() != null ) {
            request.setId(requestDTO.getId());
        }

        if (requestDTO.getStatus().equals(2)) {
            request.setClosingDate(LocalDate.now());

        }

        request.setEngineer(engineer);
        request.setMechanic(mechanic);
        request.setPriority(Priority.toEnum(requestDTO.getPriority()));
        request.setStatus(Status.toEnum(requestDTO.getStatus()));
        request.setTitle(requestDTO.getTitle());
        request.setNotes(requestDTO.getNotes());
        return request;
    }


}
