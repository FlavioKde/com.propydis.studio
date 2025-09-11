package com.propydis.studio.service.mysql;

import com.propydis.studio.shared.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.model.mysql.User;
import com.propydis.studio.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        return userRepository.save(user);
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
        userRepository.delete(existing);
    }

}
