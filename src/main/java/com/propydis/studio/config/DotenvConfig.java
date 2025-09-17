package com.propydis.studio.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class DotenvConfig {


    @PostConstruct
    public void loadEnv() {
        System.out.println("=== INICIANDO CARGA DE VARIABLES DE ENTORNO ===");

        try {
            Dotenv dotenv = Dotenv.configure()
                    .filename(".env")
                    .ignoreIfMissing()
                    .load();

            System.out.println("✅ Archivo .env encontrado y cargado");

            dotenv.entries().forEach(entry -> {
                System.setProperty(entry.getKey(), entry.getValue());
                System.out.println("🔑 Variable cargada: " + entry.getKey() + " = " +
                        (entry.getKey().contains("SECRET") || entry.getKey().contains("PASSWORD") ?
                                "[OCULTO]" : entry.getValue()));
            });

            System.out.println("=== VARIABLES DE ENTORNO CARGADAS CORRECTAMENTE ===");

        } catch (Exception e) {
            System.err.println("❌ ERROR cargando .env: " + e.getMessage());
            System.err.println("📁 Directorio actual: " + System.getProperty("user.dir"));
            e.printStackTrace();
        }
    }
}



