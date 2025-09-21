package com.propydis.studio.infrastructure.persistence.mysql.user.contact;

import com.propydis.studio.domain.user.contact.ContactStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact")
public class ContactEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false, name = "first_name")
        private String firstName;
        @Column(nullable = false, name = "last_name")
        private String lastName;
        @Column(unique = true, name = "email")
        private String email;
        @Column(unique = true, name = "phone")
        private String phone;
        @Column(nullable = false, columnDefinition = "TEXT", name = "message")
        private String message;
        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(length = 1000, name = "reply_message")
        private String replyMessage;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, name = "contact_status")
        private ContactStatus contactStatus =  ContactStatus.NEW;

        public ContactEntity() {}

        public ContactEntity(String firstName, String lastName, String email, String phone, String message, LocalDateTime created_at,String replyMessage, ContactStatus contactStatus) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.message = message;
            this.createdAt = created_at;
            this.replyMessage = replyMessage;
            this.contactStatus = contactStatus;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public String getReplyMessage() {
            return replyMessage;
        }

        public void setReplyMessage(String replyMessage) {
            this.replyMessage = replyMessage;
        }


        public ContactStatus getContactStatus() {
            return contactStatus;
        }

        public void setContactStatus(ContactStatus contactStatus) {
            this.contactStatus = contactStatus;
        }
    }


