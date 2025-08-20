package com.propydis.studio.config.mongodb.seed;



import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Project;
import com.propydis.studio.model.mongodb.ProjectStatus;
import com.propydis.studio.repository.mongodb.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class ProjectSeedConfig {

    @Bean
    CommandLineRunner projectSeedConfig(ProjectRepository projectRepository) {
        return args -> {
            if (projectRepository.count() == 0) {
                Project project1 = new Project();
                project1.setName("Casa cielo abierto");
                project1.setDescription("Mejor casa cielo abierto en Bariloche");
                project1.setCreatedAt(LocalDateTime.now());
                project1.setUpdatedAt(LocalDateTime.now());
                project1.setProjectStatus(ProjectStatus.ACTIVE);
                project1.setPhotos(Arrays.asList(
                        new Photo("https://picsum.photos/400/300", "Foto de la casa"),
                        new Photo("https://picsum.photos/400/301", "Otra vista")
                ));

                Project project2 = new Project();
                project2.setName("Edificio Las Rosas");
                project2.setDescription("Departamentos premium en Palermo");
                project2.setCreatedAt(LocalDateTime.now());
                project2.setUpdatedAt(LocalDateTime.now());
                project2.setProjectStatus(ProjectStatus.ACTIVE);
                project2.setPhotos(Arrays.asList(
                        new Photo("https://picsum.photos/400/302", "Vista exterior"),
                        new Photo("https://picsum.photos/400/303", "Interior moderno")
                ));



                projectRepository.saveAll(Arrays.asList(project1,project2));

                System.out.println("Insertados correctamente");

            }else {
                System.out.println("No se han insertado correctamente");

            }
        };
    }
}
