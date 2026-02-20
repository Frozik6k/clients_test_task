package ru.Frozik6k.clients.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.Frozik6k.clients.dto.ClientRequest;
import ru.Frozik6k.clients.dto.ClientResponse;
import ru.Frozik6k.clients.dto.ContactDto;
import ru.Frozik6k.clients.model.Client;

import java.util.List;

@Mapper(componentModel = "spring", uses = ContactDto.class)
public interface ClientMapper {
    ClientResponse toClientDto(Client client);
    Client toClient(ClientRequest clientRequest);
    List<ClientResponse> toClientDtos(List<Client> clients);

    @AfterMapping
    default void linkContacts(@MappingTarget Client client) {
        if (client.getContacts() != null) {
            client.getContacts().forEach(contact -> contact.setClient(client));
        }
    }
}
