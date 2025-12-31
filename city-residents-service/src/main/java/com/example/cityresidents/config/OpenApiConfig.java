package com.example.cityresidents.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "City Residents API",
                version = "v1",
                description = "API for managing city residents"
        )
)
@Configuration
public class OpenApiConfig {
}
