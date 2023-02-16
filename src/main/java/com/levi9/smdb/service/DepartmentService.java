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

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public boolean validateInput(String depName, String depCode) {
        String name = "([a-zA-Z]\\s?)*+";
        String code = "^[A-Za-z]*$";
        boolean validName = !depName.isEmpty() && !depName.isBlank() && Pattern.matches(name, depName);
        boolean validCode = !depCode.isEmpty() && !depCode.isBlank() && Pattern.matches(code, depCode);
        return validName && validCode;
    }

    public void saveNewDepartment(Department department) {
        String depCode = department.getDepCode();
        depCode = depCode.substring(0, 1).toUpperCase() + depCode.substring(1);
        department.setDepCode(depCode);
        departmentRepository.save(department);
    }

    public boolean validateEmail(String department, String email) {
        Set<String> depCodes = departmentRepository.getDepartmentCodes();
        String depCodeFromEmail = email.substring(email.indexOf("@") + 1, email.indexOf(".levi9.com"));
        boolean validDepartmentInEmail =
                depCodes.stream().anyMatch(depCode -> depCode.equalsIgnoreCase(depCodeFromEmail) && depCode.equalsIgnoreCase(department));
        boolean validEmailEnding = email.endsWith("levi9.com");
        return validDepartmentInEmail && validEmailEnding;
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.getDepartmentById(departmentId);
    }

    public boolean updateDepartment(Long deptId, Department department) {
        if (validateInput(department.getDepName(), department.getDepCode())) {
            Department updateDepartment = departmentRepository.getDepartmentById(deptId);
            updateDepartment.setDepCode(department.getDepCode());
            updateDepartment.setDepName(department.getDepName());
            departmentRepository.save(updateDepartment);
            return true;
        }
        return false;
    }

    public void deleteDepartment(Long deptId) {
        departmentRepository.delete(getDepartmentById(deptId));
    }
}
