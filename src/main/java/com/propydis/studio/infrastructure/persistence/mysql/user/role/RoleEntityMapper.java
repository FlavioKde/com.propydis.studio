package com.propydis.studio.infrastructure.persistence.mysql.user.role;

import com.propydis.studio.domain.user.role.Role;

public class RoleEntityMapper {


        public static Role toDomain(RoleEntity roleEntity) {
            if (roleEntity == null) {
                return null;
            }
            Role role = new Role();
            role.setId(roleEntity.getId());
            role.setRoleType(roleEntity.getRoleType());

            return  role;
        }

        public static RoleEntity toEntity(Role role) {
            if (role == null) {
                return null;
            }
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(role.getId());
            roleEntity.setRoleType(role.getRoleType());

            return roleEntity;
        }
}
