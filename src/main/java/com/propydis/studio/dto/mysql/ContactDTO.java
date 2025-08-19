package com.propydis.studio.dto.mysql;

import com.propydis.studio.model.mysql.ContactStatus;

import java.time.LocalDateTime;

public class ContactDTO {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String message;
    private LocalDateTime createdAt;
    private ContactStatus contactStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public ContactStatus getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(ContactStatus contactStatus) {
        this.contactStatus = contactStatus;
    }

}
