package ru.Frozik6k.clients.dto;

import ru.Frozik6k.clients.valid.AtLeastOneNotBlank;

import java.util.UUID;

@AtLeastOneNotBlank(message = "Необходимо указать либо телефон, либо email")
public record ContactDto(
        UUID id,
        String phone,
        String email
) {
}
