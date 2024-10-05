package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.service.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatJpaRepositoryImpl implements ChatRepository {

    private final ChatJpaRepository chatRepository;

    @Override
    public void save(MessageStatusDto messageStatusDto) {

    }

    @Override
    public List<MessageStatusDto> findByChatRoomId(Long chatRoomId) {
        return List.of();
    }

    @Override
    public String findLastMessageByChatRoomId(Long chatRoomId) {
        return "";
    }

    @Override
    public void deleteByChatRoomId(Long chatRoomId) {

    }
}
