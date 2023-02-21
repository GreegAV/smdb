package com.levi9.smdb.integration.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.levi9.smdb.entity.Department;
import com.levi9.smdb.integration.core.BaseIntegrationTest;
import com.levi9.smdb.repository.DepartmentRepository;

@SpringBootTest
class DepartmentsRepositoryTest extends BaseIntegrationTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void NotEmptyRepository() {
        Department dept = departmentRepository.getDepartmentById(0L);
        assertEquals("TestDepartmentName", dept.getDepName());

    }

}
