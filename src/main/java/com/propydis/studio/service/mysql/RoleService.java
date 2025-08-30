package com.propydis.studio.service.mysql;

import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.model.mysql.Role;
import com.propydis.studio.repository.mysql.RoleRepository;

import java.util.List;

public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role, Long id) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "role"));

        existing.setRoleType(role.getRoleType());

        return roleRepository.save(existing);
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "role"));
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void deleteById(Long id) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "role"));

        roleRepository.delete(existing);


    }
}
