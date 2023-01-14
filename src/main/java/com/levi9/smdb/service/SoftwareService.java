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

    public String countSoftwares() {
        return String.valueOf(softwareRepository.count());
    }

    public List<SoftwareDTO> getAllSoftware() {
        return softwareRepository.getAllSoftware();
    }

    public List<SoftwareDTO> getSoftwareByEmployee(Long id) {
        return softwareRepository.getSoftwareByEmployee(id);
    }

    public boolean validateInput(String softName) {
        String word = "\\w+\\s?\\w+";
        return Pattern.matches(word, softName);
    }

    public void saveNewSoftware(Software software) {
        softwareRepository.save(software);
    }
}
