package com.example.applyexecutortoservice.domain.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Chat {

    private final Long id;
    private final ChatRoom chatRoom;
    private final String sender;
    private final String message;

    @Builder
    public Chat(Long id, ChatRoom chatRoom, String sender, String message) {
        this.id = id;
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.message = message;
    }
}
