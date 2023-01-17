package com.levi9.smdb.service;

import java.util.List;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.repository.EmployeeRepository;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

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
        String word = "\\w+\\s?\\w+";
        boolean validNames = Pattern.matches(word, firstName) && Pattern.matches(word, lastName);
        boolean validDepartment = departmentService.validateDepartment(department);
        boolean validEmail = departmentService.validateEmail(department, email);
        return validNames && validDepartment && validEmail;
    }

    @Transactional
    public void saveNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}