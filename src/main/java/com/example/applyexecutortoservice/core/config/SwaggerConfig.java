package com.example.applyexecutortoservice.core.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi defaultApi() {
        Info info = new Info().title("채팅 서비스 API").version("v0.1");

        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .displayName("All API")
                .addOpenApiCustomiser(api -> api.setInfo(info))
                .build();
    }
}
