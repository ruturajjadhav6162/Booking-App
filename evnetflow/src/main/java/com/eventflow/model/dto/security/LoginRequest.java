package com.eventflow.model.dto.security;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

public record LoginRequest(
    @NotBlank String email,
    @NotBlank String password
) {}