package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.service.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatJpaRepositoryImpl implements ChatRepository {

    private final ChatJpaRepository chatRepository;
}
