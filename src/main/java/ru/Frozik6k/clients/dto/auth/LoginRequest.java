package ru.Frozik6k.clients.dto.auth;

public record LoginRequest(
        String username,
        String password
) {
}
