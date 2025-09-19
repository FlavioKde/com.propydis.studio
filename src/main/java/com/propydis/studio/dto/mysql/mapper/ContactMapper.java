package com.propydis.studio.dto.mysql.mapper;

import com.propydis.studio.dto.mysql.ContactCreateDTO;
import com.propydis.studio.dto.mysql.ContactDTO;
import com.propydis.studio.domain.user.Contact;
import com.propydis.studio.domain.user.ContactStatus;

import java.time.LocalDateTime;

public class ContactMapper {

    public static ContactDTO toDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setId(contact.getId());
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setMessage(contact.getMessage());
        contactDTO.setCreatedAt(contact.getCreatedAt());
        contactDTO.setReplyMessage(contact.getReplyMessage());
        contactDTO.setContactStatus(contact.getContactStatus());

        return contactDTO;
    }

    public static Contact toEntity(ContactCreateDTO contactCreateDTO) {
        Contact contact = new Contact();

        contact.setFirstName(contactCreateDTO.getFirstName());
        contact.setLastName(contactCreateDTO.getLastName());
        contact.setEmail(contactCreateDTO.getEmail());
        contact.setPhone(contactCreateDTO.getPhone());
        contact.setMessage(contactCreateDTO.getMessage());
        contact.setCreatedAt(LocalDateTime.now());
        contact.setContactStatus(ContactStatus.NEW);

        return contact;
    }
}
