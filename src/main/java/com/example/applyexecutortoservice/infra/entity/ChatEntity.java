package com.example.applyexecutortoservice.infra.entity;

import com.example.applyexecutortoservice.domain.chat.Chat;
import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "chats")
public class ChatEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoomEntity chatRoom;

    @Column
    private String sender;

    @Column
    private String message;

    public static ChatEntity from(Chat chat) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.id = chat.getId();
        chatEntity.sender = chat.getSender();
        chatEntity.message = chat.getMessage();

        return chatEntity;
    }

    public Chat toModel() {
        return Chat.builder()
                .id(id)
                .chatRoom(userList)
                .sender(name)
                .message()
                .build();
    }
}
