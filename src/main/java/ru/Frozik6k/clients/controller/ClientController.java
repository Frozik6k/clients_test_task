package ru.Frozik6k.clients.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.Frozik6k.clients.dto.ClientRequest;
import ru.Frozik6k.clients.dto.ClientResponse;
import ru.Frozik6k.clients.dto.error.ErrorResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Контоллер для работы с клиентами")
public interface ClientController {
    @Operation(description = "Создание нового клиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно создан новый клиент",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ClientResponse.class)
                            )
                    }
            )
    })
    ClientResponse createClient(ClientRequest clientRequest);

    @Operation(description = "Обновление данных клиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные об клиенте успешно обновлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ClientResponse.class)
                            )
                    }
            )
    })
    ClientResponse updateClient(ClientRequest clientRequest);

    @Operation(description = "Удаление клиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Клиент успешно удален"
            )
    })
    void deleteClient(UUID id);

    @Operation(description = "Получение данных клиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные о клиенте успешно получены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ClientResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Клиент не найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    }
            )
    })
    ClientResponse getClient(UUID id);

    @Operation(description = "Получение списка клиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список клиентов успешно получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class))
                            )
                    }
            )
    })
    List<ClientResponse> getAllClients();
}
