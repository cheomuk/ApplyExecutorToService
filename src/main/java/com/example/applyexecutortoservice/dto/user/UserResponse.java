package com.example.applyexecutortoservice.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    @Schema(description = "닉네임")
    private final String nickname;

    @Schema(description = "로그인 성공 메시지")
    private final String responseMessage;

    @Builder
    public UserResponse(String nickname, String responseMessage) {
        this.nickname = nickname;
        this.responseMessage = responseMessage;
    }
}
