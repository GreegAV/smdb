package com.levi9.smdb.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.levi9.smdb.entity.Department;

@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    Department department;

    @BeforeEach
    void init() {
        department = new Department();
        department.setDepCode("Depcode");
        department.setDepName("Department Name");
        department.setId(99L);
    }

    @Test
    void testCountDepartmentRepository() {
        long count = departmentRepository.count();

        assertTrue(count > 0);
    }

    @Test
    void testSaveDepartmentRepository() {
        long countBefore = departmentRepository.count();

        departmentRepository.save(department);

        long countAfter = departmentRepository.count();
        assertTrue(countBefore < countAfter);
    }

}