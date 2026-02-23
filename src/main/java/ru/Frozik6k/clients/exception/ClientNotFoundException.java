package ru.Frozik6k.clients.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        super("Client not found");
    }
}
