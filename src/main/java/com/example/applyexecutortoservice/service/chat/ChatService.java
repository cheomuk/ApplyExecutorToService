package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;

public interface ChatService {

    String saveMessage(MessageStatusDto messageStatusDto);
}
