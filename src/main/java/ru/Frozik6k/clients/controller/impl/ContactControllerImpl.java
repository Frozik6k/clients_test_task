package ru.Frozik6k.clients.controller.impl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.Frozik6k.clients.controller.ContactController;
import ru.Frozik6k.clients.dto.ContactDto;
import ru.Frozik6k.clients.service.ContactService;

import java.util.UUID;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactControllerImpl implements ContactController {

    private final ContactService contactService;

    @Override
    @PostMapping("/{clientId}")
    public ContactDto createContact(@RequestBody ContactDto contactDto, @RequestParam UUID clientId) {
        return contactService.createContact(contactDto, clientId);
    }

    @Override
    @PutMapping
    public ContactDto updateContact(@RequestBody ContactDto contactDto) {
        return contactService.updateContact(contactDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteContact(@RequestParam UUID id) {
        contactService.deleteContact(id);
    }

    @Override
    @GetMapping("/{id}")
    public ContactDto getContact(@RequestParam UUID id) {
        return contactService.getContact(id);
    }
}
