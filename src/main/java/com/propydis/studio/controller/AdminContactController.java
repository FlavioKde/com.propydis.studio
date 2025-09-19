package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mysql.ContactCreateDTO;
import com.propydis.studio.dto.mysql.ContactDTO;
import com.propydis.studio.dto.mysql.mapper.ContactMapper;
import com.propydis.studio.domain.user.Contact;
import com.propydis.studio.domain.user.ContactStatus;
import com.propydis.studio.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/admin/contact")
@PreAuthorize("hasRole('ADMIN')")
public class AdminContactController {

        private final ContactService contactService;

        public AdminContactController(ContactService contactService) {
            this.contactService = contactService;
        }


    @PutMapping("/reply/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactDTO> reply(@PathVariable long id, @RequestBody String replyMessage) {
        Contact contact = contactService.reply(id,replyMessage);

        return ResponseEntity.ok(ContactMapper.toDTO(contact));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ContactDTO>> getAll() {
        List<ContactDTO> contactDTO = contactService.findAll()
                .stream()
                .map(ContactMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(contactDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactDTO> update(@Valid @RequestBody ContactCreateDTO contactCreateDTO, @PathVariable long id) {
        Contact contact = ContactMapper.toEntity(contactCreateDTO);
        Contact  updatedContact = contactService.update(contact,id);

        return ResponseEntity.ok(ContactMapper.toDTO(updatedContact));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactDTO> getById(@PathVariable long id) {
        Contact contact = contactService.findById(id);

        return ResponseEntity.ok(ContactMapper.toDTO(contact));
    }

    @PutMapping("/mark-as-read/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        Contact contact = contactService.findById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        contact.setContactStatus(ContactStatus.VIEWED);
        contactService.save(contact);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/mark-as-viewed/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> markAsViewed(@PathVariable Long id) {
        Contact contact = contactService.findById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }

        if (contact.getContactStatus() == ContactStatus.NEW) {
            contact.setContactStatus(ContactStatus.VIEWED);
            contactService.save(contact);
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
            contactService.findById(id);

            return  ResponseEntity.noContent().build();
    }

}
