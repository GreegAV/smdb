package com.levi9.smdb.service;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.levi9.smdb.dto.DepartmentDTO;
import com.levi9.smdb.entity.Department;
import com.levi9.smdb.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String countDepartments() {
        return String.valueOf(departmentRepository.count());
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public Long getDepIdByDepCode(String depCode) {
        return departmentRepository.getAllDepartments().stream().filter(dept -> dept.getDepCode().equalsIgnoreCase(depCode)).findFirst()
                .map(DepartmentDTO::getId).orElse(null);
    }

    public boolean validateInput(String depName, String depCode) {
        return Pattern.matches("\\w+\\s?\\w+", depName) && Pattern.matches("\\w+", depCode);
    }

    public void saveNewDepartment(Department department) {
        String depCode = department.getDepCode();
        depCode = depCode.substring(0, 1).toUpperCase() + depCode.substring(1);
        department.setDepCode(depCode);
        departmentRepository.save(department);
    }

    public boolean validateDepartment(String department) {
        Set<String> depCodes = departmentRepository.getDepartmentCodes();
        return depCodes.stream().anyMatch(depCode -> depCode.equalsIgnoreCase(department.trim()));
    }

    public boolean validateEmail(String department, String email) {
        Set<String> depCodes = departmentRepository.getDepartmentCodes();
        String depCodeFromEmail = email.substring(email.indexOf("@") + 1, email.indexOf("."));
        boolean validDepartmentInEmail =
                depCodes.stream().anyMatch(depCode -> depCode.equalsIgnoreCase(depCodeFromEmail) && depCode.equalsIgnoreCase(department));
        boolean validEmailEnding = email.endsWith("levi9.com");
        return validDepartmentInEmail && validEmailEnding;
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.getDepartmentById(departmentId);
    }
}
