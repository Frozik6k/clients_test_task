package ru.Frozik6k.clients.security.service;

import ru.Frozik6k.clients.security.model.RefreshToken;

import java.util.List;
import java.util.UUID;

public interface TokenService {
    String createAccessToken(String username, List<String> roles);

    String generateRefreshToken();

    String createAndStoreRefreshToken(UUID userId);

    RefreshToken validateRefreshTokenOrThrow(String refreshToken);

    RefreshToken findByRefreshTokenOrThrow(String refreshToken);

    void updateRefreshToken(RefreshToken refreshToken);
}
