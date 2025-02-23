package com.microservices.departmentservice.controller;

import com.microservices.departmentservice.dto.DepartmentDTO;
import com.microservices.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/department")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
   private  DepartmentService departmentService;

//     build Save Department REST API
    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartment = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

//    Build get department by code REST api
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("department-code") String departmentCode) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }


}