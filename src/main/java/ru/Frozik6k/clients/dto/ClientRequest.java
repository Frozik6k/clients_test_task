package ru.Frozik6k.clients.dto;

import ru.Frozik6k.clients.valid.AtLeastOneNotBlank;

import java.util.UUID;

@AtLeastOneNotBlank
public record ClientRequest(
        UUID clientId,
        String name,
        String lastName
) {
}
