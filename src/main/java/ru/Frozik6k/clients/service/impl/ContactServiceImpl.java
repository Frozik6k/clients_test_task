package ru.Frozik6k.clients.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Frozik6k.clients.dto.ContactDto;
import ru.Frozik6k.clients.exception.ClientNotFoundException;
import ru.Frozik6k.clients.exception.ContactNotFoundException;
import ru.Frozik6k.clients.mapper.ContactMapper;
import ru.Frozik6k.clients.model.Client;
import ru.Frozik6k.clients.model.Contact;
import ru.Frozik6k.clients.repository.ClientRepository;
import ru.Frozik6k.clients.repository.ContactRepository;
import ru.Frozik6k.clients.service.ContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;
    private final ContactMapper contactMapper;

    @Override
    public ContactDto createContact(ContactDto contactDto, UUID clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        Contact contact = contactMapper.toContact(contactDto);

        contact.setClient(client);

        if (client.getContacts() == null) {
            client.setContacts(new ArrayList<>());
        }
        client.getContacts().add(contact);

        Contact saved = contactRepository.save(contact);

        return contactMapper.toContactDto(saved);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        if (contactDto.id() == null) throw new ContactNotFoundException();
        Contact contact = contactRepository.findById(contactDto.id()).orElseThrow(ContactNotFoundException::new);
        contact.setPhone(contactDto.phone());
        contact.setEmail(contactDto.email());
        Contact saved = contactRepository.save(contact);
        return contactMapper.toContactDto(saved);
    }

    @Override
    public void deleteContact(UUID id) {
        contactRepository.deleteById(id);
    }

    @Override
    public ContactDto getContact(UUID id) {
        Contact contact = contactRepository.findById(id).orElseThrow(ContactNotFoundException::new);
        return contactMapper.toContactDto(contact);
    }
}
