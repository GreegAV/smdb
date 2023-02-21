package com.levi9.smdb.integration.test;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.integration.core.BaseIntegrationTest;
import com.levi9.smdb.repository.EmployeeRepository;

@SpringBootTest
class EmployeeRepositoryTest extends BaseIntegrationTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void NotEmptyRepository() {
        Assertions.assertNotNull(employeeRepository.getAllEmployees());
    }

    @Test
    void addingNewEmployee() {
        int before = employeeRepository.getAllEmployees().size();
        Employee employee = new Employee();
        employee.setId(99L);
        employee.setEmail("test@test.com");
        employee.setLastName("LastName");
        employee.setFirstName("FirstName");
        employee.setLogin("Login");
        employee.setIsAdmin(false);
        employeeRepository.save(employee);
        int after = employeeRepository.getAllEmployees().size();
        Assertions.assertTrue(before < after);
        System.out.println(before);
        System.out.println(after);
    }

    @Test
    void getSavedEmployee() {
        Optional<Employee> emp = employeeRepository.findByLogin("Login");
        Assertions.assertNotNull(emp.get());
        System.out.println(emp.get().getId() + "   " + emp.get().getLastName());
    }
}
