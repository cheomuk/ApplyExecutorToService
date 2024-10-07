package com.example.applyexecutortoservice.dto.chat;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomDto {

    @Schema(description = "마지막 채팅")
    private String lastMessage;

    @Schema(description = "채팅방 정보")
    private ChatRoom chatRoom;

    @Builder
    public ChatRoomDto(String lastMessage, ChatRoom chatRoom) {
        this.lastMessage = lastMessage;
        this.chatRoom = chatRoom;
    }
}
