package ru.Frozik6k.clients.dto;

import java.util.UUID;

public record ContactDto(
        UUID id,
        String phone,
        String email
) {
}
