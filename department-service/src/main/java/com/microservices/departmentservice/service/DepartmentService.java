package com.microservices.departmentservice.service;

import com.microservices.departmentservice.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

}