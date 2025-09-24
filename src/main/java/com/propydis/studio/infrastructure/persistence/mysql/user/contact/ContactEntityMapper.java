package com.propydis.studio.infrastructure.persistence.mysql.user.contact;

import com.propydis.studio.domain.user.contact.Contact;

public class ContactEntityMapper {

        public static Contact toDomain(ContactEntity contactEntity) {
            if (contactEntity == null) {
                return null;
            }
            Contact contact = new Contact();
            contact.setId(contactEntity.getId());
            contact.setFirstName(contactEntity.getFirstName());
            contact.setLastName(contactEntity.getLastName());
            contact.setEmail(contactEntity.getEmail());
            contact.setPhone(contactEntity.getPhone());
            contact.setMessage(contactEntity.getMessage());
            contact.setReplyMessage(contactEntity.getReplyMessage());
            contact.setCreatedAt(contactEntity.getCreatedAt());
            contact.setContactStatus(contactEntity.getContactStatus());

            return contact;
        }

        public static ContactEntity toEntity(Contact contact) {
            if (contact == null) {
                return null;
            }
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setId(contact.getId());
            contactEntity.setFirstName(contact.getFirstName());
            contactEntity.setLastName(contact.getLastName());
            contactEntity.setEmail(contact.getEmail());
            contactEntity.setPhone(contact.getPhone());
            contactEntity.setMessage(contact.getMessage());
            contactEntity.setReplyMessage(contact.getReplyMessage());
            contactEntity.setCreatedAt(contact.getCreatedAt());
            contactEntity.setContactStatus(contact.getContactStatus());

            return contactEntity;
        }
}
