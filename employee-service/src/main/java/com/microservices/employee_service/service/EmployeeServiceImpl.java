package com.microservices.employee_service.service;

import com.microservices.employee_service.dto.APIResponseDTO;
import com.microservices.employee_service.dto.DepartmentDTO;
import com.microservices.employee_service.dto.EmployeeDTO;
import com.microservices.employee_service.entity.Employee;
//import com.microservices.employee_service.mapper.EmployeeMapper;
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
        Employee employee = employeeRepository.findById(employeeId).get();

        ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/department/"+ employee.getDepartmentCode(), DepartmentDTO.class);
        DepartmentDTO departmentDTO = responseEntity.getBody();

        EmployeeDTO employeeDTO = new EmployeeDTO(
             employee.getId(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getEmail(),
             employee.getDepartmentCode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);

        return apiResponseDTO;
    }
}