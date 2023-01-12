package com.levi9.smdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "select d.id, d.dep_name, d.dep_code from departments d", nativeQuery = true)
    List<Department> getAllDepartments();
}
