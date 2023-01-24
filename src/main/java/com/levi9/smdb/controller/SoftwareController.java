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
import org.springframework.web.bind.annotation.RequestParam;

import com.levi9.smdb.dto.AssignSoftwareToEmployeeDTO;
import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.entity.Employee;
import com.levi9.smdb.entity.Software;
import com.levi9.smdb.service.EmployeeService;
import com.levi9.smdb.service.SoftwareService;

@Controller
@AllArgsConstructor
@RequestMapping("/software")
public class SoftwareController {

    private static final String ERROR = "error";
    private final SoftwareService softwareService;
    private final EmployeeService employeeService;

    @GetMapping("/software")
    public String software(Model model) {
        List<SoftwareDTO> softList = softwareService.getAllSoftware();
        model.addAttribute("softList", softList);
        return "/software/software";
    }

    @GetMapping("/addsoftware")
    public String addSoftware() {
        return "/software/addsoftware";
    }

    @PostMapping("/addsoftware")
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

    @GetMapping("/assignsoftware")
    public String assignSoftware(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        List<SoftwareDTO> softList = softwareService.getUnassignedSoftware();
        model.addAttribute("softList", softList);

        model.addAttribute("soft2empl", new AssignSoftwareToEmployeeDTO());

        return "/software/assignsoftware";
    }

    @GetMapping("/assignsoftware/{id}")
    public String assignSoftwareForEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        List<SoftwareDTO> softList = softwareService.getUnassignedSoftware();
        model.addAttribute("softList", softList);

        model.addAttribute("soft2empl", new AssignSoftwareToEmployeeDTO());

        return "/software/assignsoftwareemployee";
    }

    @PostMapping("/assignsoftware")
    public String assignSoftwarePerform(@ModelAttribute("soft2empl") AssignSoftwareToEmployeeDTO soft2empl) {
        Software software = null;
        if (soft2empl.getSoftwareId() != null) {
            software = softwareService.findSoftById(soft2empl.getSoftwareId());
        }
        Employee employee = null;
        if (soft2empl.getEmployeeId() != null) {
            employee = employeeService.getEmployeeById(soft2empl.getEmployeeId());
        }
        if (!employeeService.validateAndAssignSoftware(software, employee)) {
            return ERROR;
        }
        return "redirect:/software/software";
    }

    @GetMapping("/{id}/edit")
    public String editSoftware(@PathVariable("id") Long id, Model model) {
        Software software = softwareService.getSoftwareById(id);
        model.addAttribute("software", software);
        return "/software/edit";
    }

    @PatchMapping("/{id}")
    public String editSoftwarePerform(@ModelAttribute("software") Software software, @PathVariable("id") Long id) {
        if (softwareService.updateSoftware(id, software)) {
            return "redirect:/software/software";
        }
        return ERROR;
    }
}
