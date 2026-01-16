package com.viraj.sample.dto;

public class EmployeeDTO {

    private Long employeeId;
    private String employeeName;
    private String employeeDescription;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long employeeId, String employeeName, String employeeDescription) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDescription = employeeDescription;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDescription() {
        return employeeDescription;
    }

    public void setEmployeeDescription(String employeeDescription) {
        this.employeeDescription = employeeDescription;
    }
}
