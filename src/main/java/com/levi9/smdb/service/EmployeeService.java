package com.levi9.smdb.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.entity.Software;
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
        String word = "([a-zA-Z]\\s?)*+";
        boolean validNames = Pattern.matches(word, firstName) && Pattern.matches(word, lastName);
        boolean validDepartment = departmentService.validateDepartment(department);
        boolean validEmail = departmentService.validateEmail(department, email);
        return validNames && validDepartment && validEmail;
    }

    @Transactional
    public void saveNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.orElse(null);
    }

    public boolean validateAndAssignSoftware(Software software, Employee employee) {
        Set<Software> employeeSoftSet = employee.getSoftware();
        Set<String> employeeSoftNames = employeeSoftSet.stream().map(Software::getSoftName).collect(Collectors.toSet());

        if (employeeSoftNames.contains(software.getSoftName())) {
            return false;
        }
        software.setAssignedTo(employee.getId());
        employeeSoftSet.add(software);
        employee.setSoftware(employeeSoftSet);
        employeeRepository.save(employee);
        return true;
    }

    public List<Employee> getEmployeesByDepartmentId(Long depId) {
        return employeeRepository.getEmployeesByDepartmentId(depId);
    }
}