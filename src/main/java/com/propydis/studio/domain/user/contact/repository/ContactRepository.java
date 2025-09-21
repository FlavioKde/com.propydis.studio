package com.propydis.studio.domain.user.contact.repository;

import com.propydis.studio.domain.user.contact.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

        Contact save(Contact contact);
        Optional<Contact> findById(Long id);
        List<Contact> findAll();
        Contact update(Contact contact, Long id);
        Contact reply(Long id, String replyMessage);
        void deleteById(Long id);

}
