package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.infra.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatJpaRepository extends JpaRepository<ChatEntity, Long> {

    List<ChatEntity> findByChatRoomId(Long chatRoomId);

    void deleteByChatRoomId(Long chatRoomId);
}
