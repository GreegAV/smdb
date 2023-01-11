package com.levi9.smdb.service;

import org.springframework.stereotype.Service;

import com.levi9.smdb.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String countDepartments() {
        return String.valueOf(departmentRepository.departmentsQuantity());
    }
}
