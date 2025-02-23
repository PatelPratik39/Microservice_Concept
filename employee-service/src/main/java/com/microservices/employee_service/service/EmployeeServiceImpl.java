package com.microservices.employee_service.service;

import com.microservices.employee_service.dto.APIResponseDTO;
import com.microservices.employee_service.dto.DepartmentDTO;
import com.microservices.employee_service.dto.EmployeeDTO;
import com.microservices.employee_service.entity.Employee;
//import com.microservices.employee_service.mapper.EmployeeMapper;
import com.microservices.employee_service.exception.ResourceNotFoundException;
import com.microservices.employee_service.repository.EmployeeRepository;
import jakarta.el.ELManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
//                convert Employee DTO to Employee Entity
        Employee employee = new Employee(
               employeeDTO.getId(),
               employeeDTO.getFirstName(),
               employeeDTO.getLastName(),
               employeeDTO.getEmail(),
               employeeDTO.getDepartmentCode()
        );
        Employee savedEmployee = employeeRepository.save(employee);

//        convert Employee Entity to EmployeeDTO

        EmployeeDTO savedEmployeeDTO = new EmployeeDTO(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
        return savedEmployeeDTO;
    }

    @Override
    public APIResponseDTO getEmployeeById(Long employeeId) {
        // Handle case where employee does not exist
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        DepartmentDTO departmentDTO = null;
        String message = "Department not found for department code: " + employee.getDepartmentCode();
        try {
            // Call department microservice
            ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity(
                    "http://localhost:8081/api/department/" + employee.getDepartmentCode(),
                    DepartmentDTO.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                departmentDTO = responseEntity.getBody();
                message = "Department found successfully : " + departmentDTO.getDepartmentCode();
            }
        } catch (Exception ex) {
            System.out.println("Error fetching department details: " + ex.getMessage());
        }

        // Convert Employee Entity -> DTO
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        // Build API Response
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setMessage(message);

        if (departmentDTO != null) {
            apiResponseDTO.setDepartment(departmentDTO);
        } else {
            apiResponseDTO.setMessage("Department not found for department code: " + employee.getDepartmentCode());
        }

        return apiResponseDTO;
    }

}