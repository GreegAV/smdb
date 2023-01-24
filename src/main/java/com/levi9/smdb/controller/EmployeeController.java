package com.levi9.smdb.controller;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        return "/employee/addemployee";
    }

    @PostMapping("/addemployee")
    public String addEmployeePerform(@ModelAttribute("employee") Employee employee) {
        if (employeeService.saveNewEmployee(employee)) {
            return "redirect:/employee/employees";
        } else {
            return ERROR;
        }
    }

    @GetMapping("/{id}/edit")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "/employee/edit";
    }

    @PatchMapping("/{id}")
    public String editEmployeePerform(@ModelAttribute("employee") Employee employee, @PathVariable("id") Long id) {
        if (employeeService.updateEmployee(id, employee)) {
            return "redirect:/employee/employees";
        }
        return ERROR;
    }

    @GetMapping("/{id}/dismiss")
    public String dismissEmployee(@PathVariable("id") Long id) {
        employeeService.dismissEmployee(id);
        return "redirect:/employee/employees";
    }
}
