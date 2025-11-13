package com.example.backexamen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Examen")
                        .version("1.0")
                        .description("Documentación de la API de Examen")
                        .contact(new Contact()
                                .name("Soporte")
                                .email("soporte@ejemplo.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
                        .description("Documentación adicional")
                        .url("https://ejemplo.com/docs"));
    }
}
