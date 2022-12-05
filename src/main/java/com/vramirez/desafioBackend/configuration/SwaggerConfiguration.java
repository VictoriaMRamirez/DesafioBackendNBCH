package com.vramirez.desafioBackend.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("Productos")
                .description("API para consulta de productos")
                .version("V1.0.0")
                .contact(new Contact()
                        .name("Victoria Ramírez")
                        .url("https://github.com/VictoriaMRamirez")
                        .email("victoriaramirezdev@gmail.com"))
                .license(new License()
                        .name("Repositorio Desafío Backend para NBCH")
                        .url("https://github.com/VictoriaMRamirez/DesafioBackendNBCH")));
    }

}
