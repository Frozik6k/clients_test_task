package ru.Frozik6k.clients.security.exception;

public class UserDisabledException extends AuthException {
    public UserDisabledException() {
        super("User is disabled");
    }
}
