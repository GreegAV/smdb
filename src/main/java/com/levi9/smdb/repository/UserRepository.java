package com.levi9.smdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.levi9.smdb.entity.Employee;

@Repository
public interface UserRepository extends CrudRepository<Employee, Long> {}
