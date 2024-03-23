package com.portfolio.security.controllers;

import com.portfolio.security.services.JWTService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTControllers {
    private final JWTService service;

    public JWTControllers(JWTService service) {
        this.service = service;
    }

    @PostMapping("/auth/signin")
    public String getJwtToken(@RequestBody SignInDto signInDto) {
        return service.createJWTToken(signInDto.getLogin(), signInDto.getPassword());
    }
}
