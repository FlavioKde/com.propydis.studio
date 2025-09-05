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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    @WithMockUser(username = "FirstName", roles = "USER")
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
        //contact.setCreatedAt(LocalDateTime.now());
        contact.setContactStatus(ContactStatus.NEW);

        Mockito.when(contactService.save(Mockito.any()))
                .thenReturn(contact);

        mockMvc.perform(post("/api/v0.1/contact/save")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("FirstName"))
                .andExpect(jsonPath("$.lastName").value("LastName"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.phone").value("1234567890"))
                .andExpect(jsonPath("$.message").value("Message"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.contactStatus").value("NEW"));

    }

    @Test
    @WithMockUser(username = "Flavio", roles = "USER")
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
        contact.setContactStatus(ContactStatus.VIEWED);

        Mockito.when(contactService.update(Mockito.any(), Mockito.eq(1L)))
                .thenReturn(contact);

        mockMvc.perform(put("/api/v0.1/contact/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Flavio"))
                .andExpect(jsonPath("$.lastName").value("Davis"))
                .andExpect(jsonPath("$.email").value("flavio@example.com"))
                .andExpect(jsonPath("$.phone").value("1234567890"))
                .andExpect(jsonPath("$.message").value("Updated Message"))
                .andExpect(jsonPath("$.replyMessage").value("Message_Reply"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.contactStatus").value("VIEWED"));

    }

    @Test
    @WithMockUser(username = "Flavio", roles = "USER")
    public void testGetContact() throws Exception {
        Contact contact = new Contact();

        contact.setId(1L);
        contact.setFirstName("Flavio");
        contact.setLastName("Davis");
        contact.setEmail("flavio@example.com");
        contact.setPhone("1234567890");
        contact.setMessage("Message test");
        contact.setReplyMessage("Message_Reply");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setContactStatus(ContactStatus.VIEWED);

        Mockito.when(contactService.findById(Mockito.anyLong())).thenReturn(contact);

        mockMvc.perform(get("/api/v0.1/contact/get/{id}", 1L))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Flavio"))
                .andExpect(jsonPath("$.lastName").value("Davis"))
                .andExpect(jsonPath("$.email").value("flavio@example.com"))
                .andExpect(jsonPath("$.phone").value("1234567890"))
                .andExpect(jsonPath("$.message").value("Message test"))
                .andExpect(jsonPath("$.replyMessage").value("Message_Reply"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.contactStatus").value("VIEWED"));

    }

}
