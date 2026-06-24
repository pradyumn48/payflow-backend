package com.pradyumn.payflow.controller;

import org.springframework.web.bind.annotation.*;

import com.pradyumn.payflow.dto.LoginRequest;
import com.pradyumn.payflow.dto.LoginResponse;
import com.pradyumn.payflow.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}