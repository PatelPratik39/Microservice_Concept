package com.microservices.employee_service.service.impl;

import com.microservices.employee_service.dto.APIResponseDTO;
import com.microservices.employee_service.dto.DepartmentDTO;
import com.microservices.employee_service.dto.EmployeeDTO;
import com.microservices.employee_service.entity.Employee;
//import com.microservices.employee_service.mapper.EmployeeMapper;
import com.microservices.employee_service.exception.ResourceNotFoundException;
import com.microservices.employee_service.repository.EmployeeRepository;
import com.microservices.employee_service.service.APIClient;
import com.microservices.employee_service.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private WebClient.Builder webClient;
    private APIClient apiClient;
    //    private RestTemplate restTemplate;

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
//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDTO getEmployeeById(Long employeeId) {

        LOGGER.info(" $$$$$$$ Fetching employee details for ID: {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));


        APIResponseDTO apiResponseDTO = new APIResponseDTO();

        DepartmentDTO departmentDTO = null;
        String message = " Department not found for department code: " + employee.getDepartmentCode();

        try {
            departmentDTO = webClient
                    .build()
                    .get()
                    .uri("http://localhost:8081/api/department/" + employee.getDepartmentCode())
                    .retrieve()
                    .bodyToMono(DepartmentDTO.class)
                    .block(); // Blocking to wait for response

            if (departmentDTO != null) {
                apiResponseDTO.setDepartment(departmentDTO);
                message = "Department found successfully: " + departmentDTO.getDepartmentCode();
            }

        } catch (Exception ex) {
            message = "Error fetching department details: " + ex.getMessage();
            throw ex;
        }
        // Create EmployeeDTO
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        // Build API Response DTO
        apiResponseDTO.setEmployee(employeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);
        apiResponseDTO.setMessage(message);

        return apiResponseDTO;
    }

    /**
     * Fall Back Method
     */
    public APIResponseDTO getDefaultDepartment(Long employeeId,Exception exception) {
        LOGGER.info(" Inside  ************ Fetching Default Department details for ID: {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setMessage("Fallback response: Unable to fetch department details due to: " + exception.getMessage());


        DepartmentDTO departmentDTO = new  DepartmentDTO();
        departmentDTO.setDepartmentName("R&D Department");
        departmentDTO.setDepartmentCode("RD001");
        departmentDTO.setDepartmentDescription("Research and Development Department");

        apiResponseDTO.setDepartment(departmentDTO);

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
//        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(employeeDTO);
//        apiResponseDTO.setDepartment(departmentDTO);

        return apiResponseDTO;
    }

}


    /**
     * **Fallback method for Circuit Breaker**
     */
//    public APIResponseDTO getDefaultDepartment(Long employeeId, Throwable throwable) {
//        log.info("inside getDefaultDepartment method");
//
//        APIResponseDTO apiResponseDTO = new APIResponseDTO();
//        apiResponseDTO.setMessage("Fallback response: Unable to fetch department details due to: " + throwable.getMessage());
//
//        // Provide a default Department
//        DepartmentDTO departmentDto = new DepartmentDTO();
//        departmentDto.setDepartmentName("R&D Department");
//        departmentDto.setDepartmentCode("RD001");
//        departmentDto.setDepartmentDescription("Research and Development Department");
//
//        apiResponseDTO.setDepartment(departmentDto);
//
//        // Fetch employee details
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElse(new Employee(0L, "Unknown", "Unknown", "unknown@example.com", "DEFAULT"));
//
//        // Convert Employee Entity to DTO
//        EmployeeDTO employeeDTO = new EmployeeDTO(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartmentCode()
//        );
//
//        apiResponseDTO.setEmployee(employeeDTO);
//        apiResponseDTO.setDepartment(departmentDto);
//        return apiResponseDTO;
//    }
//}









//    @Override
//    public APIResponseDTO getEmployeeById(Long employeeId) {
//        // Handle case where employee does not exist
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));
//
//        APIResponseDTO apiResponseDTO = new APIResponseDTO();
////        try {
////            // Call department microservice
////            ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity(
////                    "http://localhost:8081/api/department/" + employee.getDepartmentCode(),
////                    DepartmentDTO.class);
////            if (responseEntity.getStatusCode().is2xxSuccessful()) {
////                departmentDTO = responseEntity.getBody();
////                message = "Department found successfully : " + departmentDTO.getDepartmentCode();
////            }
////        } catch (Exception ex) {
////            System.out.println("Error fetching department details: " + ex.getMessage());
////        }
//
//        // Convert Employee Entity -> DTO
//        // Convert Employee Entity -> DTO
//        EmployeeDTO employeeDTO = new EmployeeDTO(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartmentCode()
//        );
//        apiResponseDTO.setEmployee(employeeDTO);
//
//        DepartmentDTO departmentDTO = null;
//        String message = "Department not found for department code: " + employee.getDepartmentCode();

//        // Call Department Microservice asynchronously using WebClient
//        // Call Department Microservice using WebClient
//        try {
//            departmentDTO = webClient
//                    .build()
//                    .get()
//                    .uri("http://localhost:8081/api/department/" + employee.getDepartmentCode())
//                    .retrieve()
//                    .bodyToMono(DepartmentDTO.class)
//                    .block(); // Blocking to wait for response
//
//            if (departmentDTO != null) {
//                apiResponseDTO.setDepartment(departmentDTO);
//                message = "Department found successfully: " + departmentDTO.getDepartmentCode();
//            }
//
//        } catch (Exception ex) {
//            message = "Error fetching department details: " + ex.getMessage();
//        }
//
//        apiResponseDTO.setDepartment(departmentDTO);
//        apiResponseDTO.setMessage(message);
//
//        return apiResponseDTO;
//
//
//    }


//        DepartmentDTO departmentDTO = webClient.build().get()
//                .uri("http://localhost:8081/api/department/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDTO.class)
//                .block();;
//        String message = "Department not found for department code: " + employee.getDepartmentCode();
//
//        // Build API Response
//        APIResponseDTO apiResponseDTO = new APIResponseDTO();
//        apiResponseDTO.setEmployee(employeeDTO);
//        apiResponseDTO.setMessage(message);
//
//        if (departmentDTO != null) {
//            apiResponseDTO.setDepartment(departmentDTO);
//        } else {
//            apiResponseDTO.setMessage("Department not found for department code: " + employee.getDepartmentCode());
//        }
//
//        return apiResponseDTO;


//}