package com.viraj.sample.controller;

import com.viraj.sample.dto.EmployeeDTO;
import com.viraj.sample.entity.Employee;
import com.viraj.sample.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/hello")
    public String getMessage() {
        return "Hello boot";
    }

    @PostMapping("/save")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = dtoToEntity(employeeDTO);
        Employee saved = employeeService.saveEmployee(employee);
        return entityToDTO(saved);
    }

    @PutMapping("/update")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = dtoToEntity(employeeDTO);
        Employee updated = employeeService.updateEmployee(employee);
        return entityToDTO(updated);
    }

    @GetMapping("/getall")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/getone/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return employee != null ? entityToDTO(employee) : null;
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    private EmployeeDTO entityToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeDescription()
        );
    }

    private Employee dtoToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        if (dto.getEmployeeId() != null) {
            employee.setEmployeeId(dto.getEmployeeId());
        }
        employee.setEmployeeName(dto.getEmployeeName());
        employee.setEmployeeDescription(dto.getEmployeeDescription());
        return employee;
    }
}
