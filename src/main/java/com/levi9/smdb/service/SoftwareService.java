package com.levi9.smdb.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.levi9.smdb.dto.SoftwareDTO;
import com.levi9.smdb.entity.Software;
import com.levi9.smdb.repository.SoftwareRepository;

@Service
public class SoftwareService {

    private final SoftwareRepository softwareRepository;

    public SoftwareService(SoftwareRepository softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    public List<SoftwareDTO> getAllSoftware() {
        return softwareRepository.getAllSoftware();
    }

    public List<SoftwareDTO> getSoftwareByEmployee(Long id) {
        return softwareRepository.getSoftwareByEmployee(id);
    }

    public boolean validateInput(String softName, String serial) {
        String word = "([a-zA-Z0-9-]\\s?)*+";
        boolean validSoftName = !softName.isBlank() && !softName.isEmpty() && Pattern.matches(word, softName);
        boolean validSerial = !serial.isBlank() && !serial.isEmpty() && Pattern.matches(word, serial);
        return validSoftName && validSerial;
    }

    public void saveNewSoftware(Software software) {
        softwareRepository.save(software);
    }

    public List<SoftwareDTO> getUnassignedSoftware() {
        return softwareRepository.getUnassignedSoftware();
    }

    public Software findSoftById(Long softId) {
        return softwareRepository.findSoftwareById(softId);
    }

    public Software getSoftwareById(Long id) {
        return softwareRepository.findSoftwareById(id);
    }

    public boolean updateSoftware(Long softId, Software software) {
        if (validateInput(software.getSoftName(), software.getSerial())) {
            Software updateSoftware = softwareRepository.findSoftwareById(softId);
            updateSoftware.setSoftName(software.getSoftName());
            updateSoftware.setSerial(software.getSerial());
            softwareRepository.save(updateSoftware);
            return true;
        }
        return false;
    }
}
