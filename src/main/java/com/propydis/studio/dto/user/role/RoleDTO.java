package com.propydis.studio.dto.user.role;

import com.propydis.studio.domain.user.role.RoleType;


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
