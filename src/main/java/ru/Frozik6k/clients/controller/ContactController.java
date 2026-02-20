package ru.Frozik6k.clients.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.Frozik6k.clients.dto.ContactDto;
import ru.Frozik6k.clients.dto.error.ErrorResponse;

import java.util.UUID;

@Tag(name = "Контроллер для работы с контактами")
public interface ContactController {
    @Operation(description = "Создание нового контакта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Контакт успешно создан",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ContactDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Клиент не найден",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ContactDto createContact(ContactDto contactDto, UUID clientId);

    @Operation(description = "Обновление данных контакта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные контакта успешно обновлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ContactDto.class)
                            )
                    }
            )
    })
    ContactDto updateContact(ContactDto contactDto);

    @Operation(description = "Удаление контакта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Контакт успешно удален"
            )
    })
    void deleteContact(UUID id);

    @Operation(description = "Получение контакта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Контакт успешно получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ContactDto.class)
                            )
                    }
            )
    })
    ContactDto getContact(UUID id);
}
