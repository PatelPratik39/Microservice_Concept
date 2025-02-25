package com.microservices.employee_service.service;

import com.microservices.employee_service.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8081", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("api/department/{departmentCode}") // ✅ Fixed Path Variable Naming
    DepartmentDTO getDepartment(@PathVariable("departmentCode") String departmentCode);


}