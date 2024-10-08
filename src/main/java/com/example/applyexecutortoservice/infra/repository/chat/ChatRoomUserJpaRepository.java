package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.infra.entity.ChatRoomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomUserJpaRepository extends JpaRepository<ChatRoomUserEntity, Long> {

    void deleteByChatRoomIdAndUserNickname(Long chatRoomId, String nickname);
}
