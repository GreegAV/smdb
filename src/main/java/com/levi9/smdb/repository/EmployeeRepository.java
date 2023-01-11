package com.levi9.smdb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "select count(*) from employees", nativeQuery = true)
    int countEmployees();
}
