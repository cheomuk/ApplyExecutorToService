package com.example.applyexecutortoservice.infra.entity;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "chat_room")
public class ChatRoomEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uid")
    private List<UserEntity> userList = new ArrayList<>();

    @Column
    private String name;

    public static ChatRoomEntity from(ChatRoom chatRoom) {
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.id = chatRoom.getId();
        chatRoomEntity.userList = chatRoom.getUserList();
        chatRoomEntity.name = chatRoom.getName();

        return chatRoomEntity;
    }

    public ChatRoom toModel() {
        return ChatRoom.builder()
                .id(id)
                .userList(userList)
                .name(name)
                .build();
    }
}
