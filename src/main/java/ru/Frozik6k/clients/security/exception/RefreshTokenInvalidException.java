package ru.Frozik6k.clients.security.exception;

public class RefreshTokenInvalidException extends AuthException {
    public RefreshTokenInvalidException(String message) {
        super(message);
    }
}
