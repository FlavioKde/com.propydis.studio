package com.propydis.studio.repository.mysql;

import com.propydis.studio.model.mysql.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {


}
