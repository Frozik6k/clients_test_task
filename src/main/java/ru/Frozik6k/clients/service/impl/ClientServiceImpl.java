package ru.Frozik6k.clients.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Frozik6k.clients.dto.ClientRequest;
import ru.Frozik6k.clients.dto.ClientResponse;
import ru.Frozik6k.clients.exception.ClientNotFoundException;
import ru.Frozik6k.clients.mapper.ClientMapper;
import ru.Frozik6k.clients.model.Client;
import ru.Frozik6k.clients.repository.ClientRepository;
import ru.Frozik6k.clients.service.ClientService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = clientMapper.toClient(clientRequest);
        return clientMapper.toClientDto(
                clientRepository.save(client));
    }

    @Override
    public ClientResponse updateClient(ClientRequest clientRequest) {
        Client client = clientMapper.toClient(clientRequest);
        return clientMapper.toClientDto(
                clientRepository.save(client));
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientResponse getClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return clientMapper.toClientDto(client);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toClientDtos(clients);
    }
}
