package com.propydis.studio.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String plainPassword = "admin123";
        String hashedPassword = encoder.encode(plainPassword);
        System.out.println("Hash: " + hashedPassword);
        System.out.println("Longitud: " + hashedPassword.length());
    }
}
