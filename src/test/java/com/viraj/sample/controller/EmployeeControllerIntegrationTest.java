package com.viraj.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viraj.sample.dto.EmployeeDTO;
import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        employeeRepository.deleteAll();
    }

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/employee/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello boot"));
    }

    @Test
    public void testSaveEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO(null, "Test User", "Tester");

        mockMvc.perform(post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Test User"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        Employee emp1 = new Employee("Usuario1", "Rol1");
        Employee emp2 = new Employee("Usuario2", "Rol2");
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);

        mockMvc.perform(get("/employee/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee("John", "Developer");
        employee = employeeRepository.save(employee);

        mockMvc.perform(get("/employee/getone/" + employee.getEmployeeId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("John"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Employee employee = new Employee("ToDelete", "Role");
        employee = employeeRepository.save(employee);

        mockMvc.perform(delete("/employee/delete/" + employee.getEmployeeId()))
                .andExpect(status().isOk());
    }
}
