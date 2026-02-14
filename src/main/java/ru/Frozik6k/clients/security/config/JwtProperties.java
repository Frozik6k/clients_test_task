package ru.Frozik6k.clients.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
        String issuer,
        long accessTtlMinutes,
        long refreshTtlDays,
        String secret
) {
}
