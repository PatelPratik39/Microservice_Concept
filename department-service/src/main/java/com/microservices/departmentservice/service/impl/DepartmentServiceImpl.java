package com.microservices.departmentservice.service.impl;

import com.microservices.departmentservice.dto.DepartmentDTO;
import com.microservices.departmentservice.entity.Department;
import com.microservices.departmentservice.mapper.DepartmentMapper;
import com.microservices.departmentservice.repository.DepartmentRepository;
import com.microservices.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.mapToDepartment(departmentDTO);  // convert DTO to Department Entity
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDTO savedDepartmentDTO = DepartmentMapper.mapToDepartmentDTO(savedDepartment);  //convert Entity to DTO
        return savedDepartmentDTO;
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDTO departmentDTO = DepartmentMapper.mapToDepartmentDTO(department);
        return departmentDTO;
    }
}