package com.levi9.smdb.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.dto.DepartmentDTO;
import com.levi9.smdb.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "select d.id, d.dep_name as depName, d.dep_code as depCode, count(e.id) as working from departments d left join employees e on "
            + "e.department_id =d.id where d.id > 0 group by d.id order by d.id", nativeQuery = true)
    List<DepartmentDTO> getAllDepartments();

    @Query(value = "select dep_code from departments", nativeQuery = true)
    Set<String> getDepartmentCodes();

    @Query(value = "select d.id, d.dep_code as depCode, d.dep_name as depName from departments d where d.id= :depId", nativeQuery = true)
    Department getDepartmentById(@Param("depId") Long depId);
}
