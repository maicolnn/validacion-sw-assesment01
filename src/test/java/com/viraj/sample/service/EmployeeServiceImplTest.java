package com.viraj.sample.service;

import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee("Maicol Nasimba", "Desarrollador de Software");
    }

    @Test
    public void testSaveEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee);

        assertNotNull(result);
        assertEquals("Maicol Nasimba", result.getEmployeeName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployee(1L);

        assertNotNull(result);
        assertEquals("Maicol Nasimba", result.getEmployeeName());
    }

    @Test
    public void testDeleteEmployee() {
        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
