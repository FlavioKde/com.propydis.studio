package com.propydis.studio.service.mysql;

import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.model.mysql.Contact;
import com.propydis.studio.model.mysql.ContactStatus;
import com.propydis.studio.repository.mysql.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact update(Contact contact, Long id) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "contact"));

        existing.setFirstName(contact.getFirstName());
        existing.setLastName(contact.getLastName());
        existing.setEmail(contact.getEmail());
        existing.setPhone(contact.getPhone());

        return contactRepository.save(existing);
    }

    public Contact findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "contact"));
    }

    public void deleteById(Long id) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(()-> new NotFoundByIdException(id, "contact"));

        contactRepository.delete(existing);
    }


    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact reply(Long id, String replyMessage) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "contact"));

        contact.setReplyMessage(replyMessage);
        contact.setContactStatus(ContactStatus.REPLIED);

        return contactRepository.save(contact);
    }

}
