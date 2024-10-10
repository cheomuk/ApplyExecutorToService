package com.example.applyexecutortoservice.dto.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomLeaveRequest {

    @Schema(description = "나가는 유저의 닉네임")
    private String nickname;
}
