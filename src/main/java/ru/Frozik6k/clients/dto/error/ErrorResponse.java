package ru.Frozik6k.clients.dto.error;

import java.time.Instant;

public record ErrorResponse(
        String error,
        String message,
        Instant timestamp,
        String path
) {
}
