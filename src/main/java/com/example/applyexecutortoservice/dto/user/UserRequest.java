package com.example.applyexecutortoservice.dto.user;

import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.domain.user.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserRequest {

    @Schema(description = "닉네임")
    private final String nickname;

    @Builder
    public UserRequest(String nickname) {
        this.nickname = nickname;
    }

    public User toDomain() {
        return User.builder()
                .uid(String.valueOf(UUID.randomUUID()))
                .nickname(nickname)
                .userRole(UserRole.USER)
                .build();
    }
}