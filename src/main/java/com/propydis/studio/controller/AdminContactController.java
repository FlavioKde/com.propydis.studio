package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mysql.ContactDTO;
import com.propydis.studio.dto.mysql.mapper.ContactMapper;
import com.propydis.studio.model.mysql.Contact;
import com.propydis.studio.service.mysql.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
