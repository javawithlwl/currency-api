package com.lwl.currency.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OpenApidocConfig {

    @Value("${module.name:Currency API}")
    private String moduleName;
    @Value("${module.version:v1}")
    private String apiVersion;
    @Bean
    @Primary
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                .addSecuritySchemes("bearerAuth",new SecurityScheme()
                    .name("bearerAuth").type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                )).info(new Info().title("Currency API").version(apiVersion));
    }
}
