package ru.Frozik6k.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.Frozik6k.clients.model.Contact;

import java.util.List;
import java.util.UUID;

public record ClientResponse(
        @JsonProperty(value = "client_id")
        UUID clientId,
        String name,
        @JsonProperty(value = "last_name")
        String lastName,
        List<Contact> contacts
) {
}
