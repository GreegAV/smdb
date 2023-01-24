package com.levi9.smdb.controller;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.service.EmployeeService;
import com.levi9.smdb.service.SoftwareService;

@Controller
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private static final String ERROR = "error";
    private final SoftwareService softwareService;
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public String employees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "/employee/employees";
    }

    @GetMapping("/{id}")
    public String employee(@PathVariable("id") Long id, Model model) {
        EmployeeDTO employee = employeeService.getEmployeeDetailById(id);
        List<SoftwareDTO> software = softwareService.getSoftwareByEmployee(id);
        model.addAttribute("employee", employee);
        model.addAttribute("software", software);
        return "/employee/employee";
    }

    @GetMapping("/addemployee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "/employee/addemployee";
    }

    @PostMapping("/addemployee")
    public String addEmployeePerform(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        if (employeeService.validateInput(firstName, lastName)) {
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDepartmentId(null);
            employee.setEmail(null);
            employeeService.saveNewEmployee(employee);
        } else {
            return ERROR;
        }
        return "redirect:/employee/employees";
    }

}
