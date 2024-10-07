package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;

import java.util.List;

public interface ChatRepository {

    void save(MessageStatusDto messageStatusDto);

    List<MessageStatusDto> findByChatRoomId(Long chatRoomId);

    void deleteByChatRoomId(Long chatRoomId);
}
