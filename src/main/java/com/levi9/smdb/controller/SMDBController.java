package com.levi9.smdb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.levi9.smdb.dto.DepartmentDTO;
import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.service.DepartmentService;
import com.levi9.smdb.service.EmployeeService;
import com.levi9.smdb.service.SoftwareService;

@Controller
public class SMDBController {

    private final DepartmentService departmentService;
    private final SoftwareService softwareService;
    private final EmployeeService employeeService;

    public SMDBController(DepartmentService departmentService, SoftwareService softwareService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.softwareService = softwareService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String startPage() {
        return "index";
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/software")
    public String software(Model model) {
        List<SoftwareDTO> software = softwareService.getAllSoftware();
        model.addAttribute("software", software);
        return "software";
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }
}
