package com.levi9.smdb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String countEmployees() {
        return String.valueOf(employeeRepository.count());
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public EmployeeDTO getEmployeeDetailById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public boolean validateInput(String firstName, String lastName, String department, String email) {
        return true;
    }

    @Transactional
    public void saveNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}