package com.propydis.studio.domain.user.user.repository;

import com.propydis.studio.domain.user.user.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    Optional<User> findByUsername(String username);


}
