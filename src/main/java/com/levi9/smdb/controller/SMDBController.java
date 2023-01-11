package com.levi9.smdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SMDBController {

    @GetMapping("/")
    public String testHello(){
        return "index";
    }
}
