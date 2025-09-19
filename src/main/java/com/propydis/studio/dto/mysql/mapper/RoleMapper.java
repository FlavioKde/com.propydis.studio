package com.propydis.studio.dto.mysql.mapper;

import com.propydis.studio.dto.mysql.RoleDTO;
import com.propydis.studio.domain.user.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleType(role.getRoleType());


        return roleDTO;
    }

    public static Role toEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRoleType(roleDTO.getRoleType());


        return role;
    }
}
