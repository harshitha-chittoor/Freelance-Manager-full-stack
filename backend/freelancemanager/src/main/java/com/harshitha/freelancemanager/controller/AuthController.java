package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.LoginRequestDto;
import com.harshitha.freelancemanager.dto.RegisterRequestDto;
import com.harshitha.freelancemanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequestDto dto) {

        return authService.register(dto);
    }

    @PostMapping("/login")
    public String login(
            @Valid @RequestBody LoginRequestDto dto) {

        return authService.login(dto);
    }
}