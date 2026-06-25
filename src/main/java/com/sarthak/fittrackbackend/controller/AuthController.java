package com.sarthak.fittrackbackend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.fittrackbackend.dto.AuthResponse;
import com.sarthak.fittrackbackend.dto.LoginRequest;
import com.sarthak.fittrackbackend.dto.RegisterRequest;
import com.sarthak.fittrackbackend.entity.User;
import com.sarthak.fittrackbackend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @GetMapping("/test")
    public String test(Authentication authentication) {

        return authentication.getName();
    }
}