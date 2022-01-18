package com.springboot.theara.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI()
    {
        OpenAPI info = new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8081/api"))
                .info(new Info()
                .title("Theara API")
                .version("Version 1.0")
                .description("This is a sample First API")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Putheara")
                        .url("https://gitlab.com/"))
        );
        return info;
    }
}
