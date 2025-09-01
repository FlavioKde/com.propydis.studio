package com.propydis.studio.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestPath = request.getServletPath();
        /*
        if (isPublicPath(requestPath)) {
            logger.debug("Ruta pública detectada: {}", requestPath);
            filterChain.doFilter(request, response);
            return;
        }


         */
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Header Authorization ausente o mal formado en ruta protegida: {}", requestPath);
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String username = jwtService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.debug("Autenticación establecida para usuario: {}", username);
            } else {
                logger.warn("Token inválido para usuario: {}", username);
            }
        }
        //voy a probar, por el metodo de tipo POST ej, or eso lo cambie
        /*
        filterChain.doFilter(request, response);

         */

        String method = request.getMethod();
        String path = request.getServletPath();

        if (isPublicPath(method, path)) {
            filterChain.doFilter(request, response);
            return;
        }




    }
// idem arriba

    /*
    private boolean isPublicPath(String path) {
        return path.equals("/api/v0.1/auth/login")
                || path.equals("/api/v0.1/auth/property")
                || path.equals("/api/v0.1/auth/project")
                || path.equals("/api/v0.1/auth/contact")

                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.equals("/swagger-ui.html");
    }

     */


    private boolean isPublicPath(String method, String path) {
        return (method.equals("POST") && path.equals("/api/v0.1/auth/login"))
                || (method.equals("POST") && path.equals("/api/v0.1/auth/contact"))
                || (method.equals("GET") && path.equals("/api/v0.1/property"))
                || (method.equals("GET") && path.startsWith("/api/v0.1/property/**"))
                || (method.equals("GET") && path.equals("/api/v0.1/project/getAll"))
                || (method.equals("GET") && path.startsWith("/api/v0.1/project/get/**"))
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.equals("/swagger-ui.html");
    }



}