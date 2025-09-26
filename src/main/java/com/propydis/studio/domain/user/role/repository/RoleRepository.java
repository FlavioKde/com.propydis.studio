package com.propydis.studio.domain.user.role.repository;

import com.propydis.studio.domain.user.role.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

        Role save(Role role);
        Optional<Role> findById(Long id);
        List<Role> findAll();
        void deleteById(Long id);

}
