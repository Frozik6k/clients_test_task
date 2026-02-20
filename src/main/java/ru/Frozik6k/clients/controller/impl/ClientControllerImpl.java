package ru.Frozik6k.clients.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.Frozik6k.clients.controller.ClientController;
import ru.Frozik6k.clients.dto.ClientRequest;
import ru.Frozik6k.clients.dto.ClientResponse;
import ru.Frozik6k.clients.service.ClientService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    @Override
    @PostMapping("/create")
    public ClientResponse createClient(@RequestBody ClientRequest clientRequest) {
        return clientService.createClient(clientRequest);
    }

    @Override
    @PutMapping("/update")
    public ClientResponse updateClient(ClientRequest clientRequest) {
        return clientService.updateClient(clientRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }

    @Override
    @GetMapping("/{id}")
    public ClientResponse getClient(@PathVariable UUID id) {
        return clientService.getClient(id);
    }

    @Override
    @GetMapping
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }
}
