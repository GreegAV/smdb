package com.levi9.smdb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.entity.Software;

@Repository
public interface SoftwareRepository extends CrudRepository<Software, Long> {
    @Query(value = "select count(*) from software", nativeQuery = true)
    int softwareQuantity();
}