package com.propydis.studio.controller;


import com.propydis.studio.dto.mysql.ContactCreateDTO;
import com.propydis.studio.dto.mysql.ContactDTO;
import com.propydis.studio.dto.mysql.mapper.ContactMapper;
import com.propydis.studio.model.mysql.Contact;
import com.propydis.studio.service.mysql.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/save")
    public ResponseEntity<ContactDTO> create(@Valid @RequestBody ContactCreateDTO contactCreateDTO) {
        Contact contact = ContactMapper.toEntity(contactCreateDTO);
        Contact  savedContact = contactService.save(contact);

        return ResponseEntity.ok(ContactMapper.toDTO(savedContact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> update(@Valid @RequestBody ContactCreateDTO contactCreateDTO, @PathVariable long id) {
        Contact contact = ContactMapper.toEntity(contactCreateDTO);
        Contact  updatedContact = contactService.update(contact,id);

        return ResponseEntity.ok(ContactMapper.toDTO(updatedContact));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDTO> getById(@PathVariable long id) {
        Contact contact = contactService.findById(id);

        return ResponseEntity.ok(ContactMapper.toDTO(contact));
    }

    @PutMapping("/reply/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactDTO> reply(@PathVariable long id, @RequestBody String replyMessage) {
        Contact contact = contactService.reply(id,replyMessage);

        return ResponseEntity.ok(ContactMapper.toDTO(contact));
    }

}
