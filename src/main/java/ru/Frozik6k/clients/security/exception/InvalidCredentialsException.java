package ru.Frozik6k.clients.security.exception;

public class InvalidCredentialsException extends AuthException {
    public InvalidCredentialsException() {
        super("Invalid credentials");
    }
}
