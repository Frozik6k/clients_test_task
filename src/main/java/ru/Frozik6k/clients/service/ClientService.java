package ru.Frozik6k.clients.service;

import ru.Frozik6k.clients.dto.ClientRequest;
import ru.Frozik6k.clients.dto.ClientResponse;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientResponse createClient(ClientRequest clientRequest);
    ClientResponse updateClient(ClientRequest clientRequest);
    void deleteClient(UUID id);
    ClientResponse getClient(UUID id);
    List<ClientResponse> getAllClients();
}
