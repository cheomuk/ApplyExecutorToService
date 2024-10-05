package com.example.applyexecutortoservice.infra.entity;

import com.example.applyexecutortoservice.domain.chat.Chat;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "chats")
public class ChatEntity {

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

    @CreatedDate
    private LocalDateTime createdDate;

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
                .chatRoom(chatRoom.toModel())
                .sender(sender)
                .message(message)
                .dateTime(createdDate)
                .build();
    }
}
