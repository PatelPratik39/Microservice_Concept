package com.microservices.departmentservice.mapper;

import com.microservices.departmentservice.dto.DepartmentDTO;
import com.microservices.departmentservice.entity.Department;

public class DepartmentMapper {

    public static DepartmentDTO mapToDepartmentDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDTO;
    }
    public static Department mapToDepartment(DepartmentDTO departmentDTO){
        Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );
        return department;
    }
}