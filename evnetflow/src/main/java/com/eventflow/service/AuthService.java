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
    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;

    public boolean userRegister(String name, String email, String password, String role) {
        if (userRepository.existsByEmail(email)) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .role(role.equalsIgnoreCase("ADMIN") ? Role.ADMIN : Role.USER)
                .build();
        try{
        userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
