package com.propydis.studio.dto.mysql.mapper;

import com.propydis.studio.dto.mysql.ContactDTO;
import com.propydis.studio.model.mysql.Contact;

public class ContactMapper {

    public static ContactDTO toDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setFirstname(contact.getFirstName());
        contactDTO.setLastname(contact.getLastName());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setMessage(contact.getMessage());
        contactDTO.setCreatedAt(contact.getCreatedAt());
        contactDTO.setContactStatus(contact.getStatus());

        return contactDTO;
    }

    public static Contact toEntity(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setFirstName(contactDTO.getFirstname());
        contact.setLastName(contactDTO.getLastname());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setMessage(contactDTO.getMessage());
        contact.setCreatedAt(contactDTO.getCreatedAt());
        contact.setStatus(contactDTO.getContactStatus());

        return contact;
    }
}
