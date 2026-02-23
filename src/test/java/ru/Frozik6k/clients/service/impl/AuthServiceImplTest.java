package ru.Frozik6k.clients.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.Frozik6k.clients.dto.auth.LoginRequest;
import ru.Frozik6k.clients.dto.auth.TokenResponse;
import ru.Frozik6k.clients.security.exception.InvalidCredentialsException;
import ru.Frozik6k.clients.security.exception.UserDisabledException;
import ru.Frozik6k.clients.security.model.User;
import ru.Frozik6k.clients.security.repository.UserRepository;
import ru.Frozik6k.clients.security.service.TokenService;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private TokenService tokenService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(UUID.randomUUID())
                .username("user")
                .passwordHash("hashed-password")
                .enabled(true)
                .roles(Set.of("ROLE_USER"))
                .build();
    }

    @Test
    void loginShouldThrowWhenUserDisabled() {
        LoginRequest request = new LoginRequest("user", "password");
        user.setEnabled(false);

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));

        assertThrows(UserDisabledException.class, () -> authService.login(request));
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(tokenService, never()).createAndStoreRefreshToken(any());
        verify(tokenService, never()).createAccessToken(anyString(), anyList());
    }

    @Test
    void loginShouldThrowWhenPasswordInvalid() {
        LoginRequest request = new LoginRequest("user", "wrong-password");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "hashed-password")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> authService.login(request));
        verify(tokenService, never()).createAndStoreRefreshToken(any());
        verify(tokenService, never()).createAccessToken(anyString(), anyList());
    }

    @Test
    void loginShouldReturnAccessAndRefreshTokens() {
        LoginRequest request = new LoginRequest("user", "correct-password");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("correct-password", "hashed-password")).thenReturn(true);
        when(tokenService.createAndStoreRefreshToken(user.getId())).thenReturn("refresh-token");
        when(tokenService.createAccessToken(eq("user"), anyList())).thenReturn("access-token");

        TokenResponse response = authService.login(request);

        assertEquals("access-token", response.accessToken());
        assertEquals("refresh-token", response.refreshToken());
        verify(tokenService).createAndStoreRefreshToken(user.getId());
        verify(tokenService).createAccessToken(eq("user"), anyList());
    }
}
