package com.eventflow.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventflow.model.dto.user.UserResponse;
import com.eventflow.model.entity.User;
import com.eventflow.service.CustomUserDetailsService;
import com.eventflow.util.JWTUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final CustomUserDetailsService userDetailsService;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    public UserResponse getMyDetails(Authentication authentication) {

        String username = authentication.getName();

        User user = (User) userDetailsService
                .loadUserByUsername(username);

        return UserResponse.from(user);
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> usersPage = userDetailsService.getAllUsers(pageable);
        return usersPage.map(UserResponse::from);
    }

    @GetMapping("/delete/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable String email) {
        return userDetailsService.deleteUserByEmail(email) ? "User deleted successfully" : "User deletion failed";
    }
    
}
