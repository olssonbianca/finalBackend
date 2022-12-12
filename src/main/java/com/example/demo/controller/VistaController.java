package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class VistaController {
    private static final Logger logger = Logger.getLogger(VistaController.class);

    @GetMapping
    public String renderIndex() {
        return "index";
    }

    @GetMapping("user")
    public String renderUser() {
        return "user";
    }

    @GetMapping("admin")
    public String renderAdmin() {
        return "admin";
    }

}
