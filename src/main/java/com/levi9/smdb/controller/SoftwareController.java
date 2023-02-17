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

import com.levi9.smdb.dto.AssignSoftwareToEmployeeDTO;
import com.levi9.smdb.dto.EmployeeDTO;
import com.levi9.smdb.dto.SoftDTO;
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
    private static final String SOFTWARE = "redirect:/software/software";
    private final SoftwareService softwareService;
    private final EmployeeService employeeService;

    @GetMapping("/software")
    public String software(Model model) {
        List<SoftwareDTO> softList = softwareService.getAllSoftware();
        model.addAttribute("softList", softList);
        return "/software/software";
    }

    @GetMapping("/addsoftware")
    public String addSoftware(@ModelAttribute("software") SoftDTO software) {
        return "/software/addsoftware";
    }

    @PostMapping("/addsoftware")
    public String addSoftwarePerform(@ModelAttribute("software") SoftDTO software) {
        String softName = software.getSoftName();
        String serial = software.getSerial();
        if (softwareService.validateInput(softName, serial)) {
            Software soft = new Software();
            soft.setSoftName(softName);
            soft.setSerial(serial);
            softwareService.saveNewSoftware(soft);
        } else {
            return ERROR;
        }
        return SOFTWARE;
    }

    @GetMapping("/assignsoftware")
    public String assignSoftware(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        List<SoftwareDTO> softList = softwareService.getUnassignedSoftware();
        model.addAttribute("softwareList", softList);

        model.addAttribute("soft2empl", new AssignSoftwareToEmployeeDTO());

        return "/software/assignsoftware";
    }

    @GetMapping("/assignsoftware/{id}")
    public String assignSoftwareForEmployee(@PathVariable("id") Long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
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
        if (software == null || employee == null || !employeeService.validateAndAssignSoftware(software, employee)) {
            return ERROR;
        }
        return SOFTWARE;
    }

    @GetMapping("/{id}/edit")
    public String editSoftware(@PathVariable("id") Long softId, Model model) {
        Software software = softwareService.getSoftwareById(softId);
        model.addAttribute("software", software);
        return "/software/edit";
    }

    @PatchMapping("/{id}")
    public String editSoftwarePerform(@ModelAttribute("software") SoftDTO software, @PathVariable("id") Long softId) {
        if (softwareService.updateSoftware(softId, software)) {
            return SOFTWARE;
        }
        return ERROR;
    }

    @GetMapping("/{id}/delete")
    public String deleteSoftware(@PathVariable("id") Long softId) {
        softwareService.deleteSoftware(softId);
        return SOFTWARE;
    }
}
