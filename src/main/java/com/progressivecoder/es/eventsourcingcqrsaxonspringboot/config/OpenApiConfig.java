package com.progressivecoder.es.eventsourcingcqrsaxonspringboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Sourcing using Axon and Spring Boot")
                        .description("App to demonstrate Event Sourcing using Axon and Spring Boot")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Rahul Sharma")
                                .url("evolve.com")
                                .email("rahul.sharma@gmail.com")
                        )
                );
    }
}
