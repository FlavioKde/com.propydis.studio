package org;

import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        contact.setContactStatus(ContactStatus.VIEWED);

        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact savedContact = contactService.save(contact);

        assertNotNull(savedContact);
        verify(contactRepository, times(1)).save(any(Contact.class));

    }

    @Test
    public void testUpdateContact() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Flavio");
        contact.setLastName("Davino");
        contact.setEmail("flavioTest@test.com");
        contact.setPhone("123456789");
        contact.setMessage("Message");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setReplyMessage("Reply");
        contact.setContactStatus(ContactStatus.VIEWED);

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.update(contact, 1L);

        assertNotNull(updatedContact);
        assertEquals("Flavio", updatedContact.getFirstName());
        verify(contactRepository, times(1)).findById(1L);
        verify(contactRepository, times(1)).save(contact);

    }

    @Test
    public void testUpdatedContact_NotFound() {

        when(contactRepository.findById(99L)).thenReturn(Optional.empty());

        Contact contact1 = new Contact();

        assertThrows(NotFoundByIdException.class,() -> contactService.update(contact1, 99L));
    }

    @Test
    public void testFindContactById() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Flavio");
        contact.setLastName("Davino");
        contact.setEmail("flavioTest@test.com");
        contact.setPhone("123456789");
        contact.setMessage("Message");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setReplyMessage("Reply");
        contact.setContactStatus(ContactStatus.VIEWED);

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Contact findByIdContact = contactService.findById(1L);
        assertEquals(contact, findByIdContact);
        verify(contactRepository, times(1)).findById(1L);

    }

    @Test
    public void testFindContactById_NotFound() {

        when(contactRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundByIdException.class, () -> contactService.findById(99L));

        verify(contactRepository, times(1)).findById(99L);

    }


    @Test
    public void testReplyContact() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Flavio");
        contact.setLastName("Davino");
        contact.setEmail("flavioTest@test.com");
        contact.setPhone("123456789");
        contact.setMessage("Message");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setReplyMessage("Reply");
        contact.setContactStatus(ContactStatus.VIEWED);

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact repliedContact = contactService.reply(1L, "Aqui estamos");

        assertNotNull(repliedContact);
        assertEquals("Aqui estamos", repliedContact.getReplyMessage());
        assertEquals(ContactStatus.REPLIED, repliedContact.getContactStatus());
        verify(contactRepository, times(1)).findById(1L);
        verify(contactRepository, times(1)).save(any(Contact.class));

    }


    @Test
    public void testReplyContact_NotFound() {

        when(contactRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundByIdException.class, () -> contactService.reply(99L, "No existe"));

        verify(contactRepository, times(1)).findById(99L);
    }
}

