package ru.Frozik6k.clients.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Frozik6k.clients.security.config.JwtProperties;
import ru.Frozik6k.clients.security.exception.RefreshTokenInvalidException;
import ru.Frozik6k.clients.security.model.RefreshToken;
import ru.Frozik6k.clients.security.repository.RefreshTokenRepository;
import ru.Frozik6k.clients.security.service.TokenService;
import ru.Frozik6k.clients.security.util.TokenHash;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String createAccessToken(String username, List<String> roles) {
        Instant now = Instant.now();
        Instant exp = now.plus(jwtProperties.accessTtlMinutes(), ChronoUnit.MINUTES);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtProperties.issuer())
                .issuedAt(now)
                .expiresAt(exp)
                .subject(username)
                .claim("roles", roles)
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    @Override
    public String generateRefreshToken() {
        byte[] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Override
    @Transactional
    public String createAndStoreRefreshToken(UUID userId) {
        String refresh = generateRefreshToken();
        String hash = TokenHash.sha256Hex(refresh);

        Instant now = Instant.now();
        Instant exp = now.plus(jwtProperties.refreshTtlDays(), ChronoUnit.MINUTES);

        refreshTokenRepository.revokeAllActiveByUserId(userId, exp);

        RefreshToken entity = RefreshToken.builder()
                .userId(userId)
                .tokenHash(hash)
                .expiresAt(exp)
                .revokedAt(null)
                .createdAt(now)
                .build();

        refreshTokenRepository.save(entity);
        return refresh;
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken validateRefreshTokenOrThrow(String refreshToken) {
        String hash = TokenHash.sha256Hex(refreshToken);

        RefreshToken rt = refreshTokenRepository.findByTokenHash(hash)
                .orElseThrow(() -> new RefreshTokenInvalidException("refresh token invalid"));

        if (rt.getRevokedAt() != null) throw new RefreshTokenInvalidException("Refresh token revoked");
        if (!rt.getExpiresAt().isAfter(Instant.now())) throw new RefreshTokenInvalidException("Refresh token expired");

        return rt;
    }

    @Override
    public RefreshToken findByRefreshTokenOrThrow(String refreshToken) {
        String hash = TokenHash.sha256Hex(refreshToken);
        return refreshTokenRepository.findByTokenHash(hash)
                .orElseThrow(() -> new RefreshTokenInvalidException("refresh token invalid"));
    }

    @Override
    public void updateRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }
}
