package com.levi9.smdb.integration.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.levi9.smdb.entity.Department;
import com.levi9.smdb.integration.core.BaseIntegrationTest;
import com.levi9.smdb.repository.DepartmentRepository;

@ExtendWith(SpringExtension.class)
@Sql(scripts = {"classpath:/db/dataset/change_department_name.sql"})
class DepartmentsRepositoryTest extends BaseIntegrationTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void NotEmptyRepository() {
        Department dept = departmentRepository.getDepartmentById(0L);
        assertEquals("TestDepartmentName", dept.getDepName());

    }

}
