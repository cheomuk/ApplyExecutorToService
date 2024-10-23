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

    @Bean
    public GroupedOpenApi userApi() {
        Info info = new Info().title("유저 & 인증/인가 API").version("v0.1");

        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/api/v1/users/**")
                .displayName("Users and Authorization")
                .addOpenApiCustomiser(api -> api.setInfo(info))
                .build();
    }

    @Bean
    public GroupedOpenApi storeApi() {
        Info info = new Info().title("채팅룸 API").version("v0.1");
        String[] paths = {"/api/v1/room/**"};

        return GroupedOpenApi.builder()
                .group("Chatting Room")
                .pathsToMatch(paths)
                .displayName("Chatting Room's API")
                .addOpenApiCustomiser(api -> api.setInfo(info))
                .build();
    }

    @Bean
    public GroupedOpenApi supportApi() {
        Info info = new Info().title("채팅 API").version("v0.1");
        String[] paths = {"/ws/chat/**"};

        return GroupedOpenApi.builder()
                .group("Chatting")
                .pathsToMatch(paths)
                .displayName("Chatting API")
                .addOpenApiCustomiser(api -> api.setInfo(info))
                .build();
    }
}
