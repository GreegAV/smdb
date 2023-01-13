package com.levi9.smdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, d.dep_name as department, e.email "
            + "from employees e join departments d on e.department_id=d.id", nativeQuery = true)
    List<EmployeeDTO> getAllEmployees();

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, d.dep_name as department, e.email "
            + "from employees e join departments d on e.department_id=d.id where e.id=?1", nativeQuery = true)
    EmployeeDTO getEmployeeById(@Param("id") Long id);
}
