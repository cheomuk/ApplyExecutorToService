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
public class MessageStatusDto {

    @Schema(description = "채팅방 ID")
    private Long chatRoomId;

    @Schema(description = "보낸 사람")
    private String sender;

    @Schema(description = "채팅 메시지")
    private String message;
}
