package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.infra.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomEntity, Long> {

}
