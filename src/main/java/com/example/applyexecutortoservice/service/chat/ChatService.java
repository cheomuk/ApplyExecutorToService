package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;

public interface ChatService {

    String saveMessage(Long id, MessageStatusDto messageStatusDto);
}
