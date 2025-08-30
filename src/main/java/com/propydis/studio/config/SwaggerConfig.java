package com.propydis.studio.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI  customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("studio")
                            .version("0.1")
                            .description("web application to sell properties and showcase architectural projects. " +
                                    "In which the user interacts with the architecture studio"))
                    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                    .components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .in(SecurityScheme.In.HEADER)
                            .name("Authorization")));


        }
}
