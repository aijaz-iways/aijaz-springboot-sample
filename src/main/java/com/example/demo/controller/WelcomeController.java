package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WelcomeController {
    @RequestMapping("/")
    public String index() {
        return "Welcome to Docker!";
    }
}
