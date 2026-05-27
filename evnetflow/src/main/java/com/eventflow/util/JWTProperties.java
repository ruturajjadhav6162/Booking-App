package com.eventflow.util;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt")
public record JWTProperties(
        String secret,
        long expiration
) {
}