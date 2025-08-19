package com.propydis.studio.dto.mysql.mapper;

import com.propydis.studio.dto.mysql.ContactCreateDTO;
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
        contactDTO.setReplyMessage(contact.getReplyMessage());
        contactDTO.setContactStatus(contact.getStatus());

        return contactDTO;
    }

    public static Contact toEntity(ContactCreateDTO contactCreateDTO) {
        Contact contact = new Contact();

        contact.setFirstName(contactCreateDTO.getFirstName());
        contact.setLastName(contactCreateDTO.getLastName());
        contact.setEmail(contactCreateDTO.getEmail());
        contact.setPhone(contactCreateDTO.getPhone());
        contact.setMessage(contactCreateDTO.getMessage());

        return contact;
    }
}
