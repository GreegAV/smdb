package com.levi9.smdb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "select count(*) from departments", nativeQuery = true)
    int departmentsQuantity();
}
