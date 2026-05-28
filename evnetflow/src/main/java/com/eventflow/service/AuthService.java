package com.eventflow.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventflow.model.entity.User;
import com.eventflow.model.entity.enums.Role;
import com.eventflow.repository.UserRepository;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public boolean userRegister(String name, String email, String password, String role) {
        return userDetailsService.saveUser(name, email, passwordEncoder.encode(password), role);
    }
}
