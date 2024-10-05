package com.example.applyexecutortoservice.infra.entity;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "chat_rooms")
public class ChatRoomEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRoom", orphanRemoval = true)
    private List<ChatRoomUserEntity> chatRoomUsers = new ArrayList<>();

    @Column
    private String name;

    public static ChatRoomEntity from(ChatRoom chatRoom) {
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.id = chatRoom.getId();
        chatRoomEntity.name = chatRoom.getName();

        return chatRoomEntity;
    }

    public ChatRoom toModel() {
        List<User> domainUsers = chatRoomUsers.stream()
                .map(ChatRoomUserEntity::getUser)
                .map(UserEntity::toModel)
                .collect(Collectors.toList());

        return ChatRoom.builder()
                .id(id)
                .name(name)
                .userList(domainUsers)
                .build();
    }
}
