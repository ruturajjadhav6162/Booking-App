package com.eventflow.util;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JWTProperties(
        String secret,
        long expiration
) {
}