package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.entities.*;
import com.mjv.mjvracingbackend.model.enums.Priority;
import com.mjv.mjvracingbackend.model.enums.Profile;
import com.mjv.mjvracingbackend.model.enums.Status;
import com.mjv.mjvracingbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;

@Service
public class DBService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instantiateDB() {

        Driver driver1 = new Driver(null, "Hamilton", "469.268.880-78", "hamilton@email.com",
                encoder.encode("123") , LocalDate.of(1996, Month.JANUARY,5));

        Engineer engineer1 = new Engineer(null, "James", "341.368.720-46", "james@email.com",
                encoder.encode("123"), LocalDate.of(1995, Month.APRIL,23));

        Manager manager1 = new Manager(null, "Rita", "601.293.870-53", "rita@email.com",
                encoder.encode("123"), LocalDate.of(2001, Month.AUGUST,10));
        manager1.addProfile(Profile.ADMIN);

        Mechanic mechanic1 = new Mechanic(null, "Lee", "817.659.510-11", "lee@email.com",
                encoder.encode("123"), LocalDate.of(1990, Month.JULY,15));

        Request request1 = new Request(null, Priority.MEDIUM, Status.PROGRESS, "Request 01",
                "Test request", engineer1, mechanic1);

        driverRepository.save(driver1);
        engineerRepository.save(engineer1);
        managerRepository.save(manager1);
        mechanicRepository.save(mechanic1);
        requestRepository.save(request1);

    }
}
