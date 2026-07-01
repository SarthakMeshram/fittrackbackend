package com.sarthak.fittrackbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fitTrackOpenAPI() {

        Contact contact = new Contact()
                .name("Sarthak Meshram")
                .url("https://github.com/SarthakMeshram")
                .email("your-email@example.com");

        Info info = new Info()
                .title("FitTrack API")
                .version("v1")
                .description("""
                        REST API for managing workouts and exercises.
                        
                        Features:
                        • JWT Authentication
                        • Workout Management
                        • Exercise Management
                        • Ownership-based Authorization
                        • Request Validation
                        """)
                .contact(contact)
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .info(info);
    }
}