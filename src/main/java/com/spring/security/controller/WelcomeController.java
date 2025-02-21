package com.spring.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "Welcome to Spring Security! " + request.getSession().getId();
    }
}
