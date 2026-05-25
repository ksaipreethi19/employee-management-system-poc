package com.task2.EmployeeProject;

import com.task2.EmployeeProject.Model.EmployeeEntity;
import com.task2.EmployeeProject.Repositary.EmployeeRepository;
import com.task2.EmployeeProject.Service.EmployeeService;
import com.task2.EmployeeProject.VallidationAndExceptionHandle.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceLayerTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    private EmployeeEntity Employee;

    @BeforeEach
    void setup() {
        Employee = new EmployeeEntity(1L, "Saipreethi", "Kothapelli", "ksai@capgemini.com", "CSE", "Java Developer", 26000.00);
    }

    @Test
    void testCreateEmployee() {
        when(repository.save(Employee)).thenReturn(Employee);
        EmployeeEntity result = service.createEmployee(Employee);
        assertEquals(Employee, result);
        verify(repository).save(Employee);
    }

    @Test
    void testUpdateEmployee()throws EmployeeNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.of(Employee));
        when(repository.save(Employee)).thenReturn(Employee);
        EmployeeEntity updated = service.updateEmployee(1L, Employee);
        assertEquals("Saipreethi", updated.getFirstName());
        verify(repository).save(Employee);
    }

    @Test
    void testGetEmployeeById() throws EmployeeNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.of(new EmployeeEntity()));
        Optional<EmployeeEntity> result = service.getEmployeeById(1L);
        assertNotNull(result);
      verify(repository,times(1)).findById(1L);
    }


    @Test
    void testDeleteEmployee() throws EmployeeNotFoundException {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deleteEmployee(1L); // no return value

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllEmployees() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<EmployeeEntity> mockPage = new PageImpl<>(List.of(Employee));

        when(repository.findAll(pageable)).thenReturn(mockPage);

        Page<EmployeeEntity> result = service.getAllEmployees(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Saipreethi", result.getContent().get(0).getFirstName());
        verify(repository).findAll(pageable);

    }


}

