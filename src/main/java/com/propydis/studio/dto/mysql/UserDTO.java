package com.propydis.studio.dto.mysql;


import java.util.List;

public class UserDTO {

    private Long id;
    private String UserName;
    private String email;
    private List<RoleDTO> role;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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
