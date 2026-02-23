package ru.Frozik6k.clients.service;

import ru.Frozik6k.clients.dto.ContactDto;

import java.util.UUID;

public interface ContactService {
    ContactDto createContact(ContactDto contactDto, UUID clientId);
    ContactDto updateContact(ContactDto contactDto);
    void deleteContact(UUID id);
    ContactDto getContact(UUID id);
}
