package com.propydis.studio.repository.mysql;

import com.propydis.studio.domain.user.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {


}
