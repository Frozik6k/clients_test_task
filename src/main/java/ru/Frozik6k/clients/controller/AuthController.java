package ru.Frozik6k.clients.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Threshold;
import ru.Frozik6k.clients.dto.auth.*;
import ru.Frozik6k.clients.dto.error.ErrorResponse;

@Tag(name = "Контроллер для работы с авторизацией и регистрацией")
public interface AuthController {
    @Operation(description = "Залогиниться и получить токен")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Токен успешно получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TokenResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Не успешная авторизация, токен не получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Не успешная авторизация, пользователь заблокирован",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    }
            )
    })
    TokenResponse login(LoginRequest loginRequest);

    @Operation(description = "Зарегистрировать нового пользователя и получить токен")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешная регистрация, токен получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TokenResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Не успешная регистрация, пользователь уже существует"
            )
    })
    TokenResponse register(RegisterRequest registerRequest);

    @Operation(description = "Запрос на получения нового access token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RefreshResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    }
            )
    })
    RefreshResponse refreshToken(RefreshRequest refreshRequest);

    @Operation(description = "Разлогинится, отзыв токенов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200"
            )
    })
    void logout(RefreshRequest refreshRequest);
}
