package com.propydis.studio.dto.mysql;

import com.propydis.studio.model.mysql.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RoleDTO {

    private Long id;

    private RoleType roleType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
