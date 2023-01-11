package com.levi9.smdb.service;

import org.springframework.stereotype.Service;

import com.levi9.smdb.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String countEmployees() {
        return String.valueOf(employeeRepository.countEmployees());
    }
}