package ru.Frozik6k.clients.service;

import ru.Frozik6k.clients.dto.auth.*;

public interface AuthService {
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse register(RegisterRequest registerRequest);
    RefreshResponse refreshToken(RefreshRequest refreshRequest);
    void logout(RefreshRequest refreshRequest);
}
