package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.MechanicDTO;
import com.mjv.mjvracingbackend.model.entities.Mechanic;
import com.mjv.mjvracingbackend.repository.MechanicRepository;
import com.mjv.mjvracingbackend.service.exception.DataIntegrityViolationException;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MechanicServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Lee";
    private static final String CPF = "817.659.510-11";
    private static final String EMAIL = "lee@email.com";
    private static final String PASSWORD = "123";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1990, Month.JULY,15);

    private static final String OBJECT_NOT_FOUND = "mechanic id not found";
    private static final String EMAIL_ALREADY_REGISTERED = "This email is already registered in our system";
    private static final String CPF_ALREADY_REGISTERED = "This CPF is already registered in our system";

    @InjectMocks
    private MechanicService mechanicService;

    @Mock
    private MechanicRepository mechanicRepository;

    @Mock
    private BCryptPasswordEncoder enconder;

    private Mechanic mechanic;
    private MechanicDTO mechanicDTO;
    private Optional<Mechanic> mechanicOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startMechanic();
    }

    @Test
    void whenFindAllThenReturnAnListOfMechanics() {
        when(mechanicRepository.findAll()).thenReturn(List.of(mechanic));

        List<Mechanic> response = mechanicService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Mechanic.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(CPF, response.get(0).getCpf());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(BIRTH_DATE, response.get(0).getBirthDate());
    }

    @Test
    void whenFindByIdReturnAnMechanic() {
        when(mechanicRepository.findById(anyLong())).thenReturn(mechanicOptional);

        Mechanic response = mechanicService.findById(ID);

        assertNotNull(response);

        assertEquals(Mechanic.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(mechanicRepository.save(any())).thenReturn(mechanic);

        Mechanic response = mechanicService.create(mechanicDTO);

        assertNotNull(response);
        assertEquals(Mechanic.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenFindByIdMechanicThenReturnAnObjectNotFoundException() {
        when(mechanicRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            mechanicService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseCPFAlreadyRegistered() {
        when(mechanicRepository.findByCpf(anyString())).thenReturn(mechanicOptional);

        try {
            mechanicOptional.get().setId(1L);
            mechanicService.create(mechanicDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(CPF_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseEmailAlreadyRegistered() {
        when(mechanicRepository.findByEmail(anyString())).thenReturn(mechanicOptional);

        try {
            mechanicOptional.get().setId(1L);
            mechanicService.create(mechanicDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(mechanicRepository.save(any())).thenReturn(mechanic);
        when(mechanicRepository.findById(anyLong())).thenReturn(mechanicOptional);

        Mechanic response = mechanicService.update(ID, mechanicDTO);

        assertNotNull(response);
        assertEquals(Mechanic.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void deleteWithSuccess() {
        when(mechanicRepository.findById(anyLong())).thenReturn(mechanicOptional);
        doNothing().when(mechanicRepository).deleteById(anyLong());
        mechanicService.delete(ID);
        verify(mechanicRepository, times(1)).deleteById(anyLong());
    }

    private void startMechanic() {
        mechanic = new Mechanic(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        mechanicDTO = new MechanicDTO(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        mechanicOptional = Optional.of(new Mechanic(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE));
    }
}