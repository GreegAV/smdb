package com.levi9.smdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.dto.DepartmentDTO;
import com.levi9.smdb.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "select d.id, d.dep_name as depName, d.dep_code as depCode, count(e.id) as working from departments d left join employees e on e.department_id =d.id group by d.id order by d.id", nativeQuery = true)
    List<DepartmentDTO> getAllDepartments();
}
