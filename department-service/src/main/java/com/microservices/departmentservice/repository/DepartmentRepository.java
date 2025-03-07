package com.microservices.departmentservice.repository;

import com.microservices.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long> {

//    Query Method
    Department findByDepartmentCode(String departmentCode);

}