package ru.Frozik6k.clients.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException() {
        super("Contact not found");
    }
}
