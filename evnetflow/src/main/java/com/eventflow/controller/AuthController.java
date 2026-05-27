package com.eventflow.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventflow.model.dto.security.LoginRequest;
import com.eventflow.model.dto.security.RegisterRequest;
import com.eventflow.service.AuthService;
import com.eventflow.service.CustomUserDetailsService;
import com.eventflow.util.JWTUtils;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JWTUtils jwtUtils;
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =authManager.authenticate(new UsernamePasswordAuthenticationToken(
                                loginRequest.email(),
                                loginRequest.password()
                        )
                );

        if (authentication.isAuthenticated()) {

            return jwtUtils.generateToken(
                    loginRequest.email()
            );
        }
        throw new RuntimeException("Invalid credentials");
        // return "login successful";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("Password"+" "+ registerRequest.password()+" "+"Email"+" "+ registerRequest.email());
        if (!authService.userRegister(registerRequest.name(), registerRequest.email(), registerRequest.password(), "USER")) {
            throw new RuntimeException("User registration failed");
        }
        return "User registered successfully";

    }
}
