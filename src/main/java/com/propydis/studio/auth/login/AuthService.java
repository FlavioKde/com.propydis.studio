package com.propydis.studio.auth.login;


import com.propydis.studio.auth.jwt.JwtService;
import com.propydis.studio.domain.user.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;
        private final UserRepository userRepository;


    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository){

            this.authenticationManager = authenticationManager;
            this.jwtService = jwtService;
            this.userRepository = userRepository;

        }

        public String authenticate(String username, String rawPassword) {



            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(username, rawPassword));

            return jwtService.generateToken((org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal());
        }



}
