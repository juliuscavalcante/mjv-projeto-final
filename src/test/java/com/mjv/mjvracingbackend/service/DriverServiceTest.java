package com.mjv.mjvracingbackend.service;

import com.mjv.mjvracingbackend.model.dto.DriverDTO;
import com.mjv.mjvracingbackend.model.entities.Driver;
import com.mjv.mjvracingbackend.repository.DriverRepository;
import com.mjv.mjvracingbackend.service.exception.DataIntegrityViolationException;
import com.mjv.mjvracingbackend.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class DriverServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Hamilton";
    private static final String CPF = "469.268.880-78";
    private static final String EMAIL = "hamilton@email.com";
    private static final String PASSWORD = "123";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1996, Month.JANUARY,5);

    private static final String OBJECT_NOT_FOUND = "driver id not found";
    private static final String EMAIL_ALREADY_REGISTERED = "This CPF is already registered in our system";
    private static final String CPF_ALREADY_REGISTERED = "This email is already registered in our system";

    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    private Driver driver;
    private DriverDTO driverDTO;
    private Optional<Driver> driverOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startDriver();
    }

    @Test
    void whenFindAllThenReturnAnListOfDrivers() {
        when(driverRepository.findAll()).thenReturn(List.of(driver));

        List<Driver> response = driverService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Driver.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(CPF, response.get(0).getCpf());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(BIRTH_DATE, response.get(0).getBirthDate());
    }

    @Test
    void whenFindByIdReturnAnDriver() {
        when(driverRepository.findById(anyLong())).thenReturn(driverOptional);

        Driver response = driverService.findById(ID);

        assertNotNull(response);

        assertEquals(Driver.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenFindByIdDriverThenReturnAnObjectNotFoundException() {
        when(driverRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            driverService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(driverRepository.save(any())).thenReturn(driver);

        Driver response = driverService.create(driverDTO);

        assertNotNull(response);
        assertEquals(Driver.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseCPFAlreadyRegistered() {
        when(driverRepository.findByCpf(anyString())).thenReturn(driverOptional);

        try {
            driverOptional.get().setId(1L);
            driverService.create(driverDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(CPF_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationExceptionBecauseEmailAlreadyRegistered() {
        when(driverRepository.findByEmail(anyString())).thenReturn(driverOptional);

        try {
            driverOptional.get().setId(1L);
            driverService.create(driverDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_REGISTERED , ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(driverRepository.save(any())).thenReturn(driver);
        when(driverRepository.findById(anyLong())).thenReturn(driverOptional);

        Driver response = driverService.update(ID, driverDTO);

        assertNotNull(response);
        assertEquals(Driver.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(BIRTH_DATE, response.getBirthDate());
    }

    @Test
    void deleteWithSuccess() {
        when(driverRepository.findById(anyLong())).thenReturn(driverOptional);
        doNothing().when(driverRepository).deleteById(anyLong());
        driverService.delete(ID);
        verify(driverRepository, times(1)).deleteById(anyLong());
    }


    private void startDriver() {
        driver = new Driver(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        driverDTO = new DriverDTO(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE);
        driverOptional = Optional.of(new Driver(ID, NAME, CPF, EMAIL, PASSWORD, BIRTH_DATE));
    }
}