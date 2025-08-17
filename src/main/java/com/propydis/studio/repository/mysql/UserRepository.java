package com.propydis.studio.repository.mysql;

import com.propydis.studio.model.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
