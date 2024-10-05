package com.example.applyexecutortoservice.domain.chat;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Chat {

    private final Long id;
    private final ChatRoom chatRoom;
    private final String sender;
    private final String message;
    private final LocalDateTime dateTime;

    @Builder
    public Chat(Long id, ChatRoom chatRoom, String sender, String message, LocalDateTime dateTime) {
        this.id = id;
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.message = message;
        this.dateTime = dateTime;
    }
}
