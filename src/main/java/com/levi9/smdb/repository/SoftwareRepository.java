package com.levi9.smdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.entity.Software;

@Repository
public interface SoftwareRepository extends CrudRepository<Software, Long> {

    @Query(value = "select s.id, s.soft_name as softName, concat( e.first_name,' ', e.last_name) as assigned, s.serial from software s left join "
            + "employees e on s.assigned_to=e.id ", nativeQuery = true)
    List<SoftwareDTO> getAllSoftware();
}