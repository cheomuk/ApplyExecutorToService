package com.example.applyexecutortoservice.infra.entity;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.domain.chat.ChatRoomUser;
import com.example.applyexecutortoservice.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "chat_room_user")
public class ChatRoomUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoomEntity chatRoom;  // 채팅방

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private UserEntity user;  // 사용자

    public static ChatRoomUserEntity from(ChatRoomUser chatRoomUser) {
        ChatRoomUserEntity chatRoomUserEntity = new ChatRoomUserEntity();
        chatRoomUserEntity.id = chatRoomUser.getId();
        chatRoomUserEntity.chatRoom = ChatRoomEntity.from(chatRoomUser.getChatRoom());
        chatRoomUserEntity.user = UserEntity.from(chatRoomUser.getUser());

        return chatRoomUserEntity;
    }
}

