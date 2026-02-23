package ru.Frozik6k.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.Frozik6k.clients.valid.AtLeastOneNotBlank;

import java.util.UUID;

@AtLeastOneNotBlank
public record ClientRequest(
        @JsonProperty(value = "client_id")
        UUID clientId,
        String name,
        @JsonProperty(value = "last_name")
        String lastName
) {
}
