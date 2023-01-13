package com.levi9.smdb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.levi9.smdb.dto.DepartmentDTO;
import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.entity.Employee;
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

    @PostMapping("/addemployee")
    public String addEmployeePerform(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("department") String department, @RequestParam("email") String email, Model model) {
        if (employeeService.validateInput(firstName, lastName, department, email)) {
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            List<DepartmentDTO> depts = departmentService.getAllDepartments();
            depts.stream().filter(dto -> dto.getDepCode().equalsIgnoreCase(department)).findFirst()
                    .ifPresent(dto -> employee.setDepartment(dto.getId()));
            employeeService.saveNewEmployee(employee);
        }

        return "redirect:/employees";
    }

    @GetMapping("/addemployee")
    public String addEmployee() {
        return "addemployee";
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employee/{id}")
    public String employee(@PathVariable("id") Long id, Model model) {
        EmployeeDTO employee = employeeService.getEmployeeDetailById(id);
        List<SoftwareDTO> software = softwareService.getSoftwareByEmployee(id);
        model.addAttribute("employee", employee);
        model.addAttribute("software", software);
        return "employee";
    }

    @GetMapping("/software")
    public String software(Model model) {
        List<SoftwareDTO> software = softwareService.getAllSoftware();
        model.addAttribute("soft", software);
        return "software";
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }
}
