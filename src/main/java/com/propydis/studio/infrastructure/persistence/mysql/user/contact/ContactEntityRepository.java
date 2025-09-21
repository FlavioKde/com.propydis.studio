package com.propydis.studio.infrastructure.persistence.mysql.user.contact;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {
}
