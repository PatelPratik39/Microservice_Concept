package com.microservices.employee_service.controller;

import com.microservices.employee_service.dto.APIResponseDTO;
import com.microservices.employee_service.dto.EmployeeDTO;
import com.microservices.employee_service.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@Tag(
        name = "Employee - Service = EmployeeController",
        description = "EmployeeController Exposes REST APIs for Employee Service"
)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(
           summary = "Save Employee REST API",
            description = "Save Employee REST API is used to save employee object into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
         EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);
         return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


//    build get Employee RESTAPI
    @Operation(
            summary = "GET Employee REST API",
            description = "GET Employee REST API is used to get employee object from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS "
    )
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDTO> getEmployee(@PathVariable("id") Long employeeId) {
        APIResponseDTO apiResponseDTO = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }
}