package com.levi9.smdb.service;

import org.springframework.stereotype.Service;

import com.levi9.smdb.repository.SoftwareRepository;

@Service
public class SoftwareService {

    private final SoftwareRepository softwareRepository;

    public SoftwareService(SoftwareRepository softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    public String countSoftwares() {
        return String.valueOf(softwareRepository.softwareQuantity());
    }
}
