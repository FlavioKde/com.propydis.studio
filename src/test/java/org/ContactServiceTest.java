package org;

import com.propydis.studio.model.mysql.Contact;
import com.propydis.studio.model.mysql.ContactStatus;
import com.propydis.studio.repository.mysql.ContactRepository;
import com.propydis.studio.service.mysql.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveContact() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("FirstName");
        contact.setLastName("LastName");
        contact.setEmail("Email");
        contact.setPhone("Phone");
        contact.setMessage("Message");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setReplyMessage("Reply");
        contact.setStatus(ContactStatus.VIEWED);

        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact savedContact = contactService.save(contact);

        assertNotNull(savedContact);
        verify(contactRepository, times(1)).save(contact);
    }
}

