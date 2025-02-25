package com.microservices.employee_service.mapper;

import com.microservices.employee_service.dto.EmployeeDTO;
import com.microservices.employee_service.entity.Employee;

public class EmployeeMapper {

//    Convert Employee entity to EmployeeDTO
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
        return employeeDTO;
    }

//    Convert EmployeeDTO to Entity class
    public static Employee mapToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail(),
                employeeDTO.getDepartmentCode()
        );
        return employee;
    }
}