package com.propydis.studio.repository.mysql;

import com.propydis.studio.domain.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
