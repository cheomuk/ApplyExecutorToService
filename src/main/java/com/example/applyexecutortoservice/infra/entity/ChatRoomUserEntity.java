package com.example.applyexecutortoservice.infra.entity;

import jakarta.persistence.*;
import lombok.Getter;

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
}

