package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.ManagerDTO;
import com.mjv.mjvracingbackend.model.entities.Manager;
import com.mjv.mjvracingbackend.repository.ManagerRepository;
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
class ManagerServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Rita";
    private static final String CPF = "601.293.870-53";
    private static final String EMAIL = "rita@email.com";
    private static final String PASSWORD = "123";
    private static final LocalDate BIRTH_DATE = LocalDate.of(2001, Month.AUGUST,10);

    private static final String OBJECT_NOT_FOUND = "manager id not found";
    private static final String EMAIL_ALREADY_REGISTERED = "This email is already registered in our system";
    private static final String CPF_ALREADY_REGISTERED = "This CPF is already registered in our system";

    @InjectMocks
    private ManagerService managerService;

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private BCryptPasswordEncoder enconder;

    private Manager manager;
    private ManagerDTO managerDTO;
    private Optional<Manager> managerOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startManager();
    }

    @Test
    void whenFindAllThenReturnAnListOfManagers() {
        when(managerRepository.findAll()).thenReturn(List.of(manager));

        List<Manager> response = managerService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Manager.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(CPF, response.get(0).getCpf());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(BIRTH_DATE, response.get(0).getBirthDate());
    }

    @Test
    void whenFindByIdReturnAnManager() {
        when(managerRepository.findById(anyLong())).thenReturn(managerOptional);

        Manager response = managerService.findById(ID);

        assertNotNull(response);

        assertEquals(Manager.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(managerRepository.save(any())).thenReturn(manager);

        Manager response = managerService.create(managerDTO);

        assertNotNull(response);
        assertEquals(Manager.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenFindByIdManagerThenReturnAnObjectNotFoundException() {
        when(managerRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            managerService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseCPFAlreadyRegistered() {
        when(managerRepository.findByCpf(anyString())).thenReturn(managerOptional);

        try {
            managerOptional.get().setId(1L);
            managerService.create(managerDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(CPF_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseEmailAlreadyRegistered() {
        when(managerRepository.findByEmail(anyString())).thenReturn(managerOptional);

        try {
            managerOptional.get().setId(1L);
            managerService.create(managerDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(managerRepository.save(any())).thenReturn(manager);
        when(managerRepository.findById(anyLong())).thenReturn(managerOptional);

        Manager response = managerService.update(ID, managerDTO);

        assertNotNull(response);
        assertEquals(Manager.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void deleteWithSuccess() {
        when(managerRepository.findById(anyLong())).thenReturn(managerOptional);
        doNothing().when(managerRepository).deleteById(anyLong());
        managerService.delete(ID);
        verify(managerRepository, times(1)).deleteById(anyLong());
    }

    private void startManager() {
        manager = new Manager(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        managerDTO = new ManagerDTO(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        managerOptional = Optional.of(new Manager(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE));
    }
}