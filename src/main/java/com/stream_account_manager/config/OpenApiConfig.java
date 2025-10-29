package com.stream_account_manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stream Account Manager API")
                        .version("1.0.0")
                        .description("API para gestión de cuentas de streaming, perfiles, pagos y suscripciones. " +
                                "Sistema diseñado para administradores que gestionan cuentas compartidas de plataformas como Netflix, HBO Max, Disney+, etc.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("contacto@streammanager.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo")
                ));
    }
}