package com.levi9.smdb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, d.dep_name as department, e.email "
            + "from employees e left join departments d on e.department_id=d.id where e.id>0", nativeQuery = true)
    List<EmployeeDTO> getAllEmployees();

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, d.dep_name as department, e.email "
            + "from employees e left join departments d on e.department_id=d.id where e.id=:id", nativeQuery = true)
    EmployeeDTO getEmployeeById(@Param("id") Long id);

    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, e.password, e.email, e.department_id as departmentId "
            + "from employees e where e.department_id= :depId", nativeQuery = true)
    List<Employee> getEmployeesByDepartmentId(@Param("depId") Long depId);

    @Query(value = "select exists(select 1 from employees e where e.email = :mail)", nativeQuery = true)
    boolean emailExists(@Param("mail") String mail);


    @Query(value = "select e.id, e.first_name as firstName, e.last_name as lastName, e.login, e.password, e.email, e.department_id as departmentId "
            + "from employees e where e.login = :login", nativeQuery = true)
    Optional<Employee> findByLogin(@Param("login") String login);

}
