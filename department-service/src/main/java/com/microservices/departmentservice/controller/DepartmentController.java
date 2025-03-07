package com.microservices.departmentservice.controller;

import com.microservices.departmentservice.dto.DepartmentDTO;
import com.microservices.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/department")
@AllArgsConstructor
@Tag(
        name = "Department Service DepartmentController",
        description = "Department Controller Exposes REST APIs for Department-Service"
)
public class DepartmentController {

    @Autowired
   private  DepartmentService departmentService;

//     build Save Department REST API
    @Operation(
            summary = "Save Department REST API",
            description = "Save Department REST API is used to save department object in a Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201- CREATED"
    )
    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartment = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

//    Build get department by code REST api
@Operation(
        summary = "Get Department REST API",
        description = "Get Department REST API is used to get department object from the Database"
)
@ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 - SUCCESS"
)
    @GetMapping("/{departmentCode}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("departmentCode") String departmentCode) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentByCode(departmentCode);
        if (departmentDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(departmentDTO);
    }


}