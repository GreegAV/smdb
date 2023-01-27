package com.levi9.smdb.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.levi9.smdb.dto.AssignDepartmentToEmployeeDTO;
import com.levi9.smdb.dto.DepartmentDTO;
import com.levi9.smdb.entity.Department;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.service.DepartmentService;
import com.levi9.smdb.service.EmployeeService;

@Controller
@AllArgsConstructor
@RequestMapping("/department")
@Api(description = "Department controller")
public class DepartmentController {

    private static final String ERROR = "error";
    private static final String DEPARTMENTS = "redirect:/department/departments";
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @GetMapping("/departments")
    @ApiOperation("List of all departments")
    public String departments(Model model) {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "/department/departments";
    }

    @GetMapping("/{id}")
    public String department(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        List<Employee> employees = employeeService.getEmployeesByDepartmentId(id);
        model.addAttribute("department", department);
        model.addAttribute("employees", employees);
        return "/department/department";
    }

    @GetMapping("/adddepartment")
    public String adddepartment() {
        return "/department/adddepartment";
    }

    @PostMapping("/adddepartment")
    public String addDepartmentPerform(@RequestParam("depName") String depName, @RequestParam("depCode") String depCode) {
        if (departmentService.validateInput(depName, depCode)) {
            Department department = new Department();
            department.setDepName(depName);
            department.setDepCode(depCode);
            departmentService.saveNewDepartment(department);
        } else {
            return ERROR;
        }
        return DEPARTMENTS;
    }

    @GetMapping("/assigndepartment/{id}")
    public String assignDepartmentForEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        List<DepartmentDTO> deptList = departmentService.getAllDepartments();
        model.addAttribute("deptList", deptList);

        model.addAttribute("dept2empl", new AssignDepartmentToEmployeeDTO());

        return "/department/assigndepartmentemployee";
    }

    @PostMapping("/assigndepartment")
    public String assignDepartmnetPerform(@ModelAttribute("dept2empl") AssignDepartmentToEmployeeDTO dept2empl) {
        Department department = null;
        if (dept2empl.getDepartmentId() != null) {
            department = departmentService.getDepartmentById(dept2empl.getDepartmentId());
        }
        Employee employee = null;
        if (dept2empl.getEmployeeId() != null) {
            employee = employeeService.getEmployeeById(dept2empl.getEmployeeId());
        }
        if (!employeeService.validateAndAssignDepartment(department, employee)) {
            return ERROR;
        }
        return "redirect:/employee/employees";
    }

    @GetMapping("/{id}/edit")
    public String editDepartment(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "/department/edit";
    }
    @PatchMapping("/{id}")
    public String editDepartmentPerform(@ModelAttribute("department") Department department, @PathVariable("id") Long id) {
        if (departmentService.updateDepartment(id, department)) {
            return DEPARTMENTS;
        }
        return ERROR;
    }
}
