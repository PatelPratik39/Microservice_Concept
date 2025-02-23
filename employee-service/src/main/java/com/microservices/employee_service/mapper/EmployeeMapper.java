package com.microservices.employee_service.mapper;

import com.microservices.employee_service.dto.EmployeeDTO;
import com.microservices.employee_service.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return employeeDTO;
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
        );
        return employee;
    }
}