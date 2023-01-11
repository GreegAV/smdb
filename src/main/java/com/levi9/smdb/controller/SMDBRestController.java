package com.levi9.smdb.controller;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.smdb.service.DepartmentService;
import com.levi9.smdb.service.EmployeeService;
import com.levi9.smdb.service.SoftwareService;

@RestController
@AllArgsConstructor
public class SMDBRestController {

    private final DepartmentService departmentService;
    private final SoftwareService softwareService;
    private final EmployeeService employeeService;

    @GetMapping("/test")
    public String testHello() {
        return "Test passed!";
    }

    @GetMapping("/count")
    public String count() {
        return departmentService.countDepartments();
    }

    @GetMapping("/count2")
    public String count2() {
        return softwareService.countSoftwares();
    }

    @GetMapping("/count3")
    public String count3() {
        return employeeService.countEmployees();
    }
}
