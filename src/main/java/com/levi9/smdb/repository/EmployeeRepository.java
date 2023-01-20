package com.levi9.smdb.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, d.dep_name as department, e.email "
            + "from employees e left join departments d on e.department_id=d.id", nativeQuery = true)
    List<EmployeeDTO> getAllEmployees();

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, d.dep_name as department, e.email "
            + "from employees e left join departments d on e.department_id=d.id where e.id=:id", nativeQuery = true)
    EmployeeDTO getEmployeeById(@Param("id") Long id);

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, e.email, e.department_id as departmentId "
            + "from employees e where e.department_id= :depId", nativeQuery = true)
    List<Employee> getEmployeesByDepartmentId(@Param("depId") Long depId);

    @Query(value = "select * from employees e where e.email like ':?1'", nativeQuery = true)
    Employee findEmployeeByEmail(@Param("email") String email);

    @Query(value = "select e.email from employees e", nativeQuery = true)
    Set<String> findAllEmails();
}
