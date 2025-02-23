package com.microservices.employee_service.service;

import com.microservices.employee_service.dto.APIResponseDTO;
import com.microservices.employee_service.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    APIResponseDTO getEmployeeById(Long employeeId);
}