package com.levi9.smdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMDBRestController {

    @GetMapping("/test")
    public String testHello(){
        return "Test passed!";
    }
}
