package com.propydis.studio.application.user.user;

import com.propydis.studio.auth.model.mysql.CustomUserDetails;
import com.propydis.studio.domain.user.user.repository.UserRepository;
import com.propydis.studio.infrastructure.persistence.mysql.user.user.UserEntityMapper;
import com.propydis.studio.infrastructure.persistence.mysql.user.user.UserEntityRepository;
import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.domain.user.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserEntityRepository userEntityRepository;

    public UserService(UserRepository userRepository, UserEntityRepository userEntityRepository) {
        this.userRepository = userRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user, Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "user"));

        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setRoles(user.getRoles());

        return userRepository.save(existing);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "user"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException(id, "user"));
        userRepository.deleteById(existing.getId());
    }








}
