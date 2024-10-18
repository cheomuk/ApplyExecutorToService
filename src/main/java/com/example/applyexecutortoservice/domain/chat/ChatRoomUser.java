package com.example.applyexecutortoservice.domain.chat;

import com.example.applyexecutortoservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ChatRoomUser {

    private Long id;
    private ChatRoom chatRoom;
    private User user;

    public ChatRoomUser() {}

    @Builder
    public ChatRoomUser(Long id, ChatRoom chatRoom, User user) {
        this.id = id;
        this.chatRoom = chatRoom;
        this.user = user;
    }
}
