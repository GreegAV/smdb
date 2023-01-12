package com.levi9.smdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.levi9.smdb.entity.Department;
import com.levi9.smdb.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String countDepartments() {
        return String.valueOf(departmentRepository.count());
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }
}
