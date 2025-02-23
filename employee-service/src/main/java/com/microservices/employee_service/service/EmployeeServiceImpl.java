package com.microservices.employee_service.service;

import com.microservices.employee_service.dto.EmployeeDTO;
import com.microservices.employee_service.entity.Employee;
import com.microservices.employee_service.mapper.EmployeeMapper;
import com.microservices.employee_service.repository.EmployeeRepository;
import jakarta.el.ELManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

//        convert Employee DTO to Employee Entity
//        Employee employee = new Employee(
//               employeeDTO.getId(),
//               employeeDTO.getFirstName(),
//               employeeDTO.getLastName(),
//               employeeDTO.getEmail()
//        );
//        Employee savedEmployee = employeeRepository.save(employee);
//
////        convert Employee Entity to EmployeeDTO
//
//        EmployeeDTO savedEmployeeDTO = new EmployeeDTO(
//                savedEmployee.getId(),
//                savedEmployee.getFirstName(),
//                savedEmployee.getLastName(),
//                savedEmployee.getEmail()
//        );

//        using Mapper class

        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO savedEmployeeDTO = EmployeeMapper.toEmployeeDTO(savedEmployee);
        return savedEmployeeDTO;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        EmployeeDTO employeeDTO = new EmployeeDTO(
             employee.getId(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getEmail()
        );

        return employeeDTO;
    }
}