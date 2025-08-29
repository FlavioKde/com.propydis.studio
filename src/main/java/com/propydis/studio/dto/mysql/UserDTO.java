package com.propydis.studio.dto.mysql;


import java.util.List;

public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private List<RoleDTO> role;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleDTO> getRole() {
            return role;
    }

    public void setRole(List<RoleDTO> role) {
        this.role = role;
    }
}
