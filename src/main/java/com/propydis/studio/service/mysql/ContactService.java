package com.propydis.studio.service.mysql;

import com.propydis.studio.model.mysql.Contact;
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

    public Contact update(Contact contact) {
        if(!contactRepository.existsById(contact.getId())){
            throw new RuntimeException("Contact not found");
        }
        return contactRepository.save(contact);
    }

    public Contact findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public void delete(Contact contact) {
        if (!contactRepository.existsById(contact.getId())){
            throw new RuntimeException("Contact not found");
        }
        contactRepository.delete(contact);
    }


    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
}
