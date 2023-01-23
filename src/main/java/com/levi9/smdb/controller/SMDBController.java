package com.levi9.smdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SMDBController {

    @GetMapping("/")
    public String startPage() {
        return "index";
    }
}
