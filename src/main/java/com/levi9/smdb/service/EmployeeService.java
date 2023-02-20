package com.levi9.smdb.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import com.levi9.smdb.dto.EmpDTO;
import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.entity.Department;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.entity.Software;
import com.levi9.smdb.repository.EmployeeRepository;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public EmployeeDTO getEmployeeDetailById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public boolean validateInput(String firstName, String lastName) {
        String word = "([a-zA-Z-]\\s?)*+";
        return !firstName.isEmpty() && !lastName.isEmpty() && Pattern.matches(word, firstName) && Pattern.matches(word, lastName);
    }

    public boolean saveNewEmployee(EmpDTO employee) {
        Employee newEmployee = new Employee();
        if (validateInput(employee.getFirstName(), employee.getLastName())) {
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setDepartmentId(null);
            newEmployee.setEmail(null);
            newEmployee.setIsAdmin(false);
            employeeRepository.save(newEmployee);
            return true;
        }
        return false;
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

    public boolean validateAndAssignDepartment(Department newDepartment, Employee employee) {
        if (newDepartment != null && employee != null) {
            employee.setDepartmentId(newDepartment.getId());
            employee.setEmail(getNewEmail(newDepartment, employee));
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    @NotNull
    private String getNewEmail(Department dept, Employee emp) {
        String email;
        String email1part = (emp.getFirstName() + (emp.getLastName().isEmpty() ? "" : "." + emp.getLastName())).toLowerCase();
        String emailLastPart = ("@" + dept.getDepCode() + ".levi9.com").toLowerCase();
        email = email1part + emailLastPart;
        if (Boolean.FALSE.equals(employeeRepository.emailExists(email))) {
            return email;
        }
        return email1part + "." + emp.getId() + emailLastPart;
    }

    public boolean updateEmployee(Long id, EmpDTO employee) {
        if (validateInput(employee.getFirstName(), employee.getLastName())) {
            Employee updateEmployee = getEmployeeById(id);
            updateEmployee.setFirstName(employee.getFirstName());
            updateEmployee.setLastName(employee.getLastName());
            employeeRepository.save(updateEmployee);
            return true;
        }
        return false;
    }

    public void dismissEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setDepartmentId(0L);
            employee.setEmail(null);
            employeeRepository.save(employee);
        }
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setDepartmentId(0L);
            employee.setEmail(null);
            employeeRepository.delete(employee);
        }
    }
}