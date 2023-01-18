package com.levi9.smdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.entity.Software;

@Repository
public interface SoftwareRepository extends CrudRepository<Software, Long> {

    @Query(value =
            "select s.id, s.soft_name as softName, concat( e.first_name,' ', e.last_name) as assignedName, s.serial, s.assigned_to as assignedTo,"
                    + "(select count(s2.id) from software s2 where s2.soft_name = s.soft_name and s2.assigned_to is null) as freeLicense  "
                    + "from software s left join employees e on s.assigned_to=e.id ", nativeQuery = true)
    List<SoftwareDTO> getAllSoftware();

    @Query(value = "select s.soft_name as softName, s.serial from software s join employees e on e.id=s.assigned_to where e.id=?1",
            nativeQuery = true)
    List<SoftwareDTO> getSoftwareByEmployee(@Param("id") Long id);

    @Query(value = "select s.id, s.soft_name as softName, s.serial from software s where s.assigned_to is null", nativeQuery = true)
    List<SoftwareDTO> getUnassignedSoftware();

    @Query("select s from Software s where s.id= :softId")
    Software findSoftwareById(@Param("softId") Long softId);
}