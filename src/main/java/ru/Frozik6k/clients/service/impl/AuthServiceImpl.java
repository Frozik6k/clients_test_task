package ru.Frozik6k.clients.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.Frozik6k.clients.dto.auth.*;
import ru.Frozik6k.clients.security.exception.*;
import ru.Frozik6k.clients.security.model.RefreshToken;
import ru.Frozik6k.clients.security.model.User;
import ru.Frozik6k.clients.security.repository.UserRepository;
import ru.Frozik6k.clients.security.service.TokenService;
import ru.Frozik6k.clients.service.AuthService;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(loginRequest.password(), user.getPasswordHash()))
            throw new InvalidCredentialsException();

        String refresh = tokenService.createAndStoreRefreshToken(user.getId());
        String access = tokenService.createAccessToken(user.getUsername(), List.copyOf(user.getRoles()));

        return new TokenResponse(access, refresh);
    }

    @Override
    public TokenResponse register(RegisterRequest registerRequest) {
        String username = registerRequest.username().trim().toLowerCase();

        if (userRepository.existsByUsername(username)) throw new UsernameAlreadyExistsException();

        String hashedPassword = passwordEncoder.encode(registerRequest.password());

        User user = User.builder()
                .roles(new HashSet<>(Set.of("ROLE_USER")))
                .username(username)
                .enabled(true)
                .passwordHash(hashedPassword)
                .build();
        userRepository.save(user);

        String refresh = tokenService.createAndStoreRefreshToken(user.getId());
        String access = tokenService.createAccessToken(user.getUsername(), List.copyOf(user.getRoles()));

        return new TokenResponse(access, refresh);
    }

    @Override
    public RefreshResponse refreshToken(RefreshRequest refreshRequest) {
        RefreshToken refreshToken = tokenService.validateRefreshTokenOrThrow(refreshRequest.refreshToken());

        User user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if (!user.isEnabled()) throw new UserDisabledException();
        String access = tokenService.createAccessToken(user.getUsername(), List.copyOf(user.getRoles()));
        return new RefreshResponse(access);
    }

    @Override
    public void logout(RefreshRequest refreshRequest) {

        RefreshToken refreshToken = tokenService.findByRefreshTokenOrThrow(refreshRequest.refreshToken());

        if (refreshToken.getRevokedAt() == null) {
            refreshToken.setRevokedAt(Instant.now());
            tokenService.updateRefreshToken(refreshToken);
        }
    }
}
