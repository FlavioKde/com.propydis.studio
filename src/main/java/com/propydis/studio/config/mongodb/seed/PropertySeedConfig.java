package com.propydis.studio.config.mongodb.seed;



import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.model.mongodb.PropertyStatus;
import com.propydis.studio.repository.mongodb.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class PropertySeedConfig {

    @Bean
    CommandLineRunner propertySeedDatabase(PropertyRepository propertyRepository) {
        return args -> {
            if (propertyRepository.count() == 0) {
                Property property1 = new Property();
                property1.setName("Departamento en Palermo");
                property1.setDescription("Lindo departamento en Palermo, a dos aguas");
                property1.setCreatedAt(LocalDateTime.now());
                property1.setUpdatedAt(LocalDateTime.now());
                property1.setPropertyStatus(PropertyStatus.AVAILABLE);
                property1.setPhotos(Arrays.asList(
                        new Photo("https://picsum.photos/400/300", "Foto de la casa"),
                        new Photo("https://picsum.photos/400/301", "Otra vista")
                ));

                Property property2 = new Property();
                property2.setName("Edificio Las Rosas");
                property2.setDescription("Departamentos premium en Palermo");
                property2.setCreatedAt(LocalDateTime.now());
                property2.setUpdatedAt(LocalDateTime.now());
                property2.setPropertyStatus(PropertyStatus.AVAILABLE);
                property2.setPhotos(Arrays.asList(
                        new Photo("https://picsum.photos/400/302", "Vista exterior"),
                        new Photo("https://picsum.photos/400/303", "Interior moderno")
                ));

                    propertyRepository.saveAll(Arrays.asList(property1, property2));

                System.out.println("Insertados correctamente");

            }else {
                System.out.println("No se han insertado correctamente");
            }
        };
    }
}
