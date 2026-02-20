package ru.Frozik6k.clients.mapper;

import org.mapstruct.Mapper;
import ru.Frozik6k.clients.dto.ContactDto;
import ru.Frozik6k.clients.model.Contact;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDto toContactDto(Contact contact);
    Contact toContact(ContactDto contactDto);
    List<ContactDto> toContactDtos(List<Contact> contacts);
    List<Contact> toContacts(List<ContactDto> contactDtos);
}
