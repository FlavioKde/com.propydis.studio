package org;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.propydis.studio.controller.ContactController;
import com.propydis.studio.dto.mysql.ContactCreateDTO;
import com.propydis.studio.dto.mysql.mapper.ContactMapper;
import com.propydis.studio.model.mysql.Contact;
import com.propydis.studio.model.mysql.ContactStatus;
import com.propydis.studio.service.mysql.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
        objectMapper = new ObjectMapper();

    }

    @Test
    public void testCreateContact() throws Exception {
        ContactCreateDTO contactCreateDTO = new ContactCreateDTO();

        contactCreateDTO.setFirstName("FirstName");
        contactCreateDTO.setLastName("LastName");
        contactCreateDTO.setEmail("test@example.com");
        contactCreateDTO.setPhone("1234567890");
        contactCreateDTO.setMessage("Message");


        Contact  contact = ContactMapper.toEntity(contactCreateDTO);

        contact.setId(1L);
        contact.setReplyMessage("Message_Reply");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setStatus(ContactStatus.NEW);

        Mockito.when(contactService.save(Mockito.any()))
                .thenReturn(contact);

        mockMvc.perform(post("/api/contact/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstname").value("FirstName"))
                .andExpect(jsonPath("$.lastname").value("LastName"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.phone").value("1234567890"))
                .andExpect(jsonPath("$.message").value("Message"))
                .andExpect(jsonPath("$.replyMessage").value("Message_Reply"))
                .andExpect(jsonPath("$.contactStatus").value("NEW"));

    }

    @Test
    public void testUpdateContact() throws Exception {
        ContactCreateDTO contactCreateDTO = new ContactCreateDTO();

        contactCreateDTO.setFirstName("Flavio");
        contactCreateDTO.setLastName("Davis");
        contactCreateDTO.setEmail("flavio@example.com");
        contactCreateDTO.setPhone("1234567890");
        contactCreateDTO.setMessage("Updated Message");

        Contact  contact = ContactMapper.toEntity(contactCreateDTO);

        contact.setId(1L);
        contact.setReplyMessage("Message_Reply");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setStatus(ContactStatus.VIEWED);

        Mockito.when(contactService.update(Mockito.any(), Mockito.eq(1L)))
                .thenReturn(contact);


        mockMvc.perform(put("/api/contact/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstname").value("Flavio"))
                .andExpect(jsonPath("$.lastname").value("Davis"))
                .andExpect(jsonPath("$.email").value("flavio@example.com"))
                .andExpect(jsonPath("$.phone").value("1234567890"))
                .andExpect(jsonPath("$.message").value("Updated Message"))
                .andExpect(jsonPath("$.replyMessage").value("Message_Reply"))
                .andExpect(jsonPath("$.contactStatus").value("VIEWED"));

    }
}
