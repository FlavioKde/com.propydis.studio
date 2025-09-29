package com.propydis.studio.infrastructure.persistence.mysql.user.contact;

import com.propydis.studio.domain.user.contact.Contact;
import com.propydis.studio.domain.user.contact.ContactStatus;
import com.propydis.studio.domain.user.contact.repository.ContactRepository;
import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ContactMysqlRepository implements ContactRepository {

        private final ContactEntityRepository contactEntityRepository;


        public ContactMysqlRepository(ContactEntityRepository contactEntityRepository) {
            this.contactEntityRepository = contactEntityRepository;
        }

        @Override
        public Contact save(Contact contact) {
            ContactEntity contactEntity = ContactEntityMapper.toEntity(contact);
            return ContactEntityMapper.toDomain(contactEntityRepository.save(contactEntity));
        }

        @Override
        public Optional<Contact>findById(Long id) {
            return contactEntityRepository.findById(id).map(ContactEntityMapper::toDomain);
        }

        @Override
        public List<Contact> findAll() {
            return contactEntityRepository.findAll().stream().map(ContactEntityMapper::toDomain).collect(Collectors.toList());
        }


        @Override
        public void deleteById(Long id) {

            contactEntityRepository.deleteById(id);
        }

        @Override
        public Contact reply(Long id, String replyMessage) {
            ContactEntity existing = contactEntityRepository.findById(id)
                    .orElseThrow(()-> new NotFoundByIdException(id, "contact"));


            existing.setReplyMessage(replyMessage);
            existing.setContactStatus(ContactStatus.REPLIED);

            return ContactEntityMapper.toDomain(contactEntityRepository.save(existing));
        }
}
