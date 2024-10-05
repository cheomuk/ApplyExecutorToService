package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.infra.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJpaRepository extends JpaRepository<ChatEntity, Long> {

}
