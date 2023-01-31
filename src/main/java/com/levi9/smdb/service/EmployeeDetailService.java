package com.levi9.smdb.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.repository.EmployeeRepository;
import com.levi9.smdb.security.EmployeeDetails;

@Service
public class EmployeeDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Employee> person = employeeRepository.findByLogin(login);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new EmployeeDetails(person.get());
    }
}
