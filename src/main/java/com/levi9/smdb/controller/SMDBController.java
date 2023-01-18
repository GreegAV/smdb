package com.levi9.smdb.controller;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.levi9.smdb.dto.AssignSoftwareToEmployeeDTO;
import com.levi9.smdb.dto.DepartmentDTO;
import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.entity.Department;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.entity.Software;
import com.levi9.smdb.service.DepartmentService;
import com.levi9.smdb.service.EmployeeService;
import com.levi9.smdb.service.SoftwareService;

@Controller
@AllArgsConstructor
public class SMDBController {

    private static final String ERROR = "error";
    private final DepartmentService departmentService;
    private final SoftwareService softwareService;
    private final EmployeeService employeeService;

    @GetMapping("/")
    public String startPage() {
        return "index";
    }

    @PostMapping("/employee/addemployee")
    public String addEmployeePerform(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("department") String department, @RequestParam("email") String email) {
        if (employeeService.validateInput(firstName, lastName, department, email)) {
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setDepartmentId(departmentService.getDepIdByDepCode(department.toUpperCase()));
            employeeService.saveNewEmployee(employee);
        } else {
            return ERROR;
        }
        return "redirect:/employee/employees";
    }

    @GetMapping("/employee/addemployee")
    public String addEmployee() {
        return "/employee/addemployee";
    }

    @PostMapping("/department/adddepartment")
    public String addDepartmentPerform(@RequestParam("depName") String depName, @RequestParam("depCode") String depCode) {
        if (departmentService.validateInput(depName, depCode)) {
            Department department = new Department();
            department.setDepName(depName);
            department.setDepCode(depCode);
            departmentService.saveNewDepartment(department);
        } else {
            return ERROR;
        }
        return "redirect:/department/departments";
    }

    @GetMapping("/department/adddepartment")
    public String adddepartment() {
        return "/department/adddepartment";
    }

    @PostMapping("/software/addsoftware")
    public String addSoftwarePerform(@RequestParam("softName") String softName, @RequestParam("serial") String serial) {
        if (softwareService.validateInput(softName, serial)) {
            Software software = new Software();
            software.setSoftName(softName);
            software.setSerial(serial);
            softwareService.saveNewSoftware(software);
        } else {
            return ERROR;
        }
        return "redirect:/software/software";
    }

    @PostMapping("/software/assignsoftware")
    public String assignSoftwarePerform(@ModelAttribute("soft2empl") AssignSoftwareToEmployeeDTO soft2empl) {
        Software software = null;
        if (soft2empl.getSoftwareId() != null) {
            software = softwareService.findSoftById(soft2empl.getSoftwareId());
        }
        Employee employee = null;
        if (soft2empl.getEmployeeId() != null) {
            employee = employeeService.getEmployeeById(soft2empl.getEmployeeId());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println(software);
        System.out.println("----------------------------------------------------------");
        System.out.println(employee);
        System.out.println("----------------------------------------------------------");

        return "redirect:/software/software";
    }

    @GetMapping("/software/addsoftware")
    public String addSoftware() {
        return "/software/addsoftware";
    }

    @GetMapping("/software/assignsoftware")
    public String assignSoftware(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        List<SoftwareDTO> softList = softwareService.getUnassignedSoftware();
        model.addAttribute("softList", softList);

        model.addAttribute("soft2empl", new AssignSoftwareToEmployeeDTO());

        return "/software/assignsoftware";
    }

    @GetMapping("/employee/employees")
    public String employees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "/employee/employees";
    }

    @GetMapping("/employee/employee/{id}")
    public String employee(@PathVariable("id") Long id, Model model) {
        EmployeeDTO employee = employeeService.getEmployeeDetailById(id);
        List<SoftwareDTO> software = softwareService.getSoftwareByEmployee(id);
        model.addAttribute("employee", employee);
        model.addAttribute("software", software);
        return "/employee/employee";
    }

    @GetMapping("/software/software")
    public String software(Model model) {
        List<SoftwareDTO> softList = softwareService.getAllSoftware();
        model.addAttribute("softList", softList);
        return "/software/software";
    }

    @GetMapping("/department/departments")
    public String departments(Model model) {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "/department/departments";
    }
}
