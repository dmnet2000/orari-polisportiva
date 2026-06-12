package com.polisportiva.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Orari Polisportiva API")
                        .version("1.0.0")
                        .description("API for managing sports facility schedules and bookings")
                        .contact(new Contact()
                                .name("Polisportiva Team")
                                .url("https://github.com/dmnet2000/orari-polisportiva")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT Auth Token")));
    }
}
