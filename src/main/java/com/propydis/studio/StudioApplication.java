package com.propydis.studio;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class StudioApplication {

	public static void main(String[] args) {

		System.out.println("=== CARGANDO VARIABLES DE ENTORNO ===");
		try {
			Dotenv dotenv = Dotenv.configure()
					.filename(".env")
					.ignoreIfMissing()
					.load();

			dotenv.entries().forEach(entry -> {
				System.setProperty(entry.getKey(), entry.getValue());
				System.out.println("Variable cargada: " + entry.getKey() +
						(entry.getKey().contains("SECRET") || entry.getKey().contains("PASSWORD") ?
								" = [OCULTO]" : " = " + entry.getValue()));
			});

			System.out.println("=== VARIABLES CARGADAS CORRECTAMENTE ===");
		} catch (Exception e) {
			System.err.println("ERROR cargando .env: " + e.getMessage());
			System.err.println("Directorio actual: " + System.getProperty("user.dir"));
		}

		SpringApplication.run(StudioApplication.class, args);
	}
}