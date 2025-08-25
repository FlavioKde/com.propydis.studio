package com.propydis.studio.service.mysql;

import com.propydis.studio.model.mysql.CustomUserDetails;
import com.propydis.studio.model.mysql.User;
import com.propydis.studio.repository.mysql.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));

        return new CustomUserDetails(user);

    }
}
