package com.propydis.studio.repository.mysql;

import com.propydis.studio.model.mysql.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
