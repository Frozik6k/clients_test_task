package ru.Frozik6k.clients.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.Frozik6k.clients.dto.error.ErrorResponse;
import ru.Frozik6k.clients.exception.ClientNotFoundException;
import ru.Frozik6k.clients.exception.ContactNotFoundException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleClientNotFoundException(ClientNotFoundException exception, HttpServletRequest request) {
        return new ErrorResponse("NOT FOUND CLIENT", exception.getMessage(), Instant.now(), request.getRequestURI());
    }

    @ExceptionHandler(ContactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleContactNotFoundException(ContactNotFoundException exception, HttpServletRequest request) {
        return new ErrorResponse("NOT FOUND CONTACT", exception.getMessage(), Instant.now(), request.getRequestURI());
    }
}
