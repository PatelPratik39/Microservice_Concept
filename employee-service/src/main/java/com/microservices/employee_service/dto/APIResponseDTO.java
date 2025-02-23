package com.microservices.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDTO {

    private EmployeeDTO employee;
    private DepartmentDTO department;
    private String message;
}