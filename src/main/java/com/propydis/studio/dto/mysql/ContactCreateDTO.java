package com.propydis.studio.dto.mysql;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContactCreateDTO {

    @NotBlank(message = "No puede quedar sin rellenar el nombre")
    @Size(max=100, message = "El nombre no puede exceder los 100 caracteres")
    private String firstName;

    @NotBlank(message = "No puede quedar sin rellenar el apellido")
    @Size(max=100, message = "El apellido no puede exceder los 100 caracteres")
    private String lastName;

    @Email(message = "Debe ser un correo válido")
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Formato de correo inválido")
    @Size(max = 200)
    private String email;

    @Pattern(regexp = "^[0-9]{10,11}$", message = "Debe ser un número de teléfono argentino válido")
    private String phone;

    @NotBlank(message = "El mensaje no puede estar vacío")
    @Size(max = 1000, message = "El mensaje no puede exceder los 1000 caracteres")
    private String message;

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

}
