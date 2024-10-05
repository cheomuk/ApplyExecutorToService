package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.infra.repository.RedisRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Builder
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final RedisRepository redisRepository;

    @Override
    public String saveMessage(Long id, MessageStatusDto messageStatusDto) {
        return "";
    }
}
