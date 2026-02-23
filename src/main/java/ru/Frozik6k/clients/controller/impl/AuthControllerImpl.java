package ru.Frozik6k.clients.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Frozik6k.clients.controller.AuthController;
import ru.Frozik6k.clients.dto.auth.*;
import ru.Frozik6k.clients.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    @PostMapping("/register")
    public TokenResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @Override
    @PostMapping("/refresh")
    public RefreshResponse refreshToken(@RequestBody RefreshRequest refreshRequest) {
        return authService.refreshToken(refreshRequest);
    }

    @Override
    @PostMapping("/logout")
    public void logout(@RequestBody RefreshRequest refreshRequest) {
        authService.logout(refreshRequest);
    }
}
