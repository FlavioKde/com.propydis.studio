package org;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.propydis.studio.controller.AdminContactController;
import com.propydis.studio.model.mysql.Contact;
import com.propydis.studio.model.mysql.ContactStatus;
import com.propydis.studio.service.mysql.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminContactControllerTest {

        private MockMvc mockMvc;

        private ObjectMapper objectMapper;

        @Mock
        private ContactService contactService;

        @InjectMocks
        private AdminContactController adminContactController;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
                mockMvc = MockMvcBuilders.standaloneSetup(adminContactController).build();
                objectMapper = new ObjectMapper();
        }


    @Test
    @WithMockUser(username = "Flavio", roles = "ADMIN")
    public void testReplyContact() throws Exception {
        Contact contact = new Contact();

        contact.setId(1L);
        contact.setFirstName("Flavio");
        contact.setLastName("Davis");
        contact.setEmail("flavio@example.com");
        contact.setPhone("1234567890");
        contact.setMessage("Message test");
        contact.setReplyMessage("Message_Reply");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setStatus(ContactStatus.VIEWED);

        Mockito.when(contactService.reply(Mockito.eq(1L), Mockito.anyString()))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    String replyMsg = invocation.getArgument(1);

                    contact.setId(id);
                    contact.setReplyMessage(replyMsg);
                    contact.setStatus(ContactStatus.REPLIED);

                    return contact;
                });


        mockMvc.perform(put("/api/v0.1/admin/contact/reply/{id}", 1L)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("Respuesta desde test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.replyMessage").value("Respuesta desde test"))
                .andExpect(jsonPath("$.contactStatus").value("REPLIED"));
    }
}
