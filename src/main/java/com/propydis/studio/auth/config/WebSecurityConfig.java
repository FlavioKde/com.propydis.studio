package com.propydis.studio.auth.config;


import com.propydis.studio.auth.jwt.JwtAuthenticationFilter;
import com.propydis.studio.auth.jwt.JwtService;
import com.propydis.studio.auth.login.AuthService;
import io.jsonwebtoken.io.Decoders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//Añadido 3-9

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;






//añadido 3-9
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig {


    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(true);

        return providerManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        //public login
                        .requestMatchers(HttpMethod.POST, "/api/v0.1/auth/login").permitAll()

                        //public data
                        .requestMatchers(HttpMethod.GET,  "/api/v0.1/property/getAll").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/v0.1/property/get/**").permitAll()
                        .requestMatchers(HttpMethod.POST,  "/api/v0.1/contact/save").permitAll()
                        .requestMatchers(HttpMethod.POST,  "/api/v0.1/contact").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/v0.1/project/getAll").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/v0.1/project/get/**").permitAll()

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/error"
                        ).permitAll()
                        .requestMatchers("/api/v0.1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))


                //comentado por que no autentica bin, supuestamente en tiempor real 3-9
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

                //añadido por el cambio de arriba
                /*
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )

                 */

                //);



        return http.build();
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() {


        return new JwtAuthenticationFilter(jwtService(), userDetailsService);
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }


    //añadido 3-9


    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        return jwt -> {
            var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
            authoritiesConverter.setAuthorityPrefix("");
            authoritiesConverter.setAuthoritiesClaimName("roles");

            var authorities = authoritiesConverter.convert(jwt);
            return new JwtAuthenticationToken(jwt, authorities);
        };
    }



    //añadido 3-9

    @Value("${jwt.secret}")
    private String SECRET_KEY;


    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }



}
