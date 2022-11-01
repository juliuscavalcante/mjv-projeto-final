package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.EngineerDTO;
import com.mjv.mjvracingbackend.model.entities.Engineer;
import com.mjv.mjvracingbackend.repository.EngineerRepository;
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
class EngineerServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "James";
    private static final String CPF = "341.368.720-46";
    private static final String EMAIL = "james@email.com";
    private static final String PASSWORD = "123";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1995, Month.APRIL,23);

    private static final String OBJECT_NOT_FOUND = "engineer id not found";
    private static final String EMAIL_ALREADY_REGISTERED = "This email is already registered in our system";
    private static final String CPF_ALREADY_REGISTERED = "This CPF is already registered in our system";

    @InjectMocks
    private EngineerService engineerService;

    @Mock
    private EngineerRepository engineerRepository;

    @Mock
    private BCryptPasswordEncoder enconder;

    private Engineer engineer;
    private EngineerDTO engineerDTO;
    private Optional<Engineer> engineerOptional;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startEngineer();
    }

    @Test
    void whenFindAllThenReturnAnListOfEngineers() {
        when(engineerRepository.findAll()).thenReturn(List.of(engineer));

        List<Engineer> response = engineerService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Engineer.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(CPF, response.get(0).getCpf());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(BIRTH_DATE, response.get(0).getBirthDate());
    }

    @Test
    void whenFindByIdReturnAnEngineer() {
        when(engineerRepository.findById(anyLong())).thenReturn(engineerOptional);

        Engineer response = engineerService.findById(ID);

        assertNotNull(response);

        assertEquals(Engineer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(engineerRepository.save(any())).thenReturn(engineer);

        Engineer response = engineerService.create(engineerDTO);

        assertNotNull(response);
        assertEquals(Engineer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenFindByIdEngineerThenReturnAnObjectNotFoundException() {
        when(engineerRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            engineerService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseCPFAlreadyRegistered() {
        when(engineerRepository.findByCpf(anyString())).thenReturn(engineerOptional);

        try {
            engineerOptional.get().setId(1L);
            engineerService.create(engineerDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(CPF_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseEmailAlreadyRegistered() {
        when(engineerRepository.findByEmail(anyString())).thenReturn(engineerOptional);

        try {
            engineerOptional.get().setId(1L);
            engineerService.create(engineerDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(engineerRepository.save(any())).thenReturn(engineer);
        when(engineerRepository.findById(anyLong())).thenReturn(engineerOptional);

        Engineer response = engineerService.update(ID, engineerDTO);

        assertNotNull(response);
        assertEquals(Engineer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void deleteWithSuccess() {
        when(engineerRepository.findById(anyLong())).thenReturn(engineerOptional);
        doNothing().when(engineerRepository).deleteById(anyLong());
        engineerService.delete(ID);
        verify(engineerRepository, times(1)).deleteById(anyLong());
    }

    private void startEngineer() {
        engineer = new Engineer(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        engineerDTO = new EngineerDTO(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        engineerOptional = Optional.of(new Engineer(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE));
    }
}