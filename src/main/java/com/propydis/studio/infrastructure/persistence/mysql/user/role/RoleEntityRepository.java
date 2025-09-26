package com.propydis.studio.infrastructure.persistence.mysql.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
}
