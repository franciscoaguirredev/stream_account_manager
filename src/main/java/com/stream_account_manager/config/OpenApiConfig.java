package com.stream_account_manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stream Account Manager API")
                        .version("1.0.0")
                        .description("API para la administración de suscriptores, cuentas y pagos de plataformas de streaming.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo - Stream Account Manager")
                                .email("soporte@streammanager.com")
                                .url("https://streammanager.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
