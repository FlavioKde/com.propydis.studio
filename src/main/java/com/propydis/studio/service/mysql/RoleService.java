package com.propydis.studio.service.mysql;

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

    public Role update(Role role) {
        if(!roleRepository.existsById(role.getId())) {
            throw new RuntimeException("Role not found");
        }
        return roleRepository.save(role);
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void deleteById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}
