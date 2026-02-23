package ru.Frozik6k.clients.security.exception;

public class UsernameAlreadyExistsException extends AuthException {
    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }
}
