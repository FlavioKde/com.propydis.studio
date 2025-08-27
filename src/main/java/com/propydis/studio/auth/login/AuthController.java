package com.propydis.studio.auth.login;


import com.propydis.studio.auth.dto.AuthRequest;
import com.propydis.studio.auth.dto.AuthResponse;
import com.propydis.studio.config.ApiConfig;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/auth")
public class AuthController {

        private final AuthService authService;

        public AuthController(AuthService authService) {
            this.authService = authService;
        }

        @PermitAll
        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login (@RequestBody AuthRequest authRequest){

                String token = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());

                return ResponseEntity.ok(new AuthResponse(token));
        }
}
