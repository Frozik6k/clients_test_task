package ru.Frozik6k.clients.security.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.Frozik6k.clients.dto.error.ErrorResponse;
import ru.Frozik6k.clients.security.exception.*;

import java.time.Instant;

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception, HttpServletRequest request) {
        return new ErrorResponse("CONFLICT", exception.getMessage(), Instant.now(), request.getRequestURI());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidCredentialsException(InvalidCredentialsException exception, HttpServletRequest request) {
        return new ErrorResponse("UNAUTHORIZED", exception.getMessage(), Instant.now(), request.getRequestURI());
    }

    @ExceptionHandler(RefreshTokenInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleRefreshTokenInvalidException(RefreshTokenInvalidException exception, HttpServletRequest request) {
        return new ErrorResponse("UNAUTHORIZED", exception.getMessage(), Instant.now(), request.getRequestURI());
    }

    @ExceptionHandler(UserDisabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleUserDisabledException(UserDisabledException exception, HttpServletRequest request) {
        return new ErrorResponse("FORBIDDEN", exception.getMessage(), Instant.now(), request.getRequestURI());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        return new ErrorResponse("UNAUTHORIZED", exception.getMessage(), Instant.now(), request.getRequestURI());
    }

}
