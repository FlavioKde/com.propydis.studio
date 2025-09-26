package com.propydis.studio.application.user.role;

import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.domain.user.role.Role;
import com.propydis.studio.domain.user.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

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

        roleRepository.deleteById(existing.getId());


    }
}
