package com.propydis.studio.domain.user.user;

import com.propydis.studio.domain.user.role.Role;


import java.util.ArrayList;
import java.util.List;


public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Role> roles;



    public User() {}

    public User(String username, String email, String password, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = new ArrayList<>();
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
            return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
