package ru.Frozik6k.clients.dto;

import ru.Frozik6k.clients.model.Contact;

import java.util.List;
import java.util.UUID;

public record ClientResponse(
       UUID clientId,
       String name,
       String lastName,
       List<Contact> contacts
) {
}
