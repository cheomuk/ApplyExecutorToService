package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.ChatMessageDto;

import java.util.List;

public interface ChatRoomService {

    void createChatRoom(List<String> userList);

    List<ChatRoom> getChatRoomLists();

    ChatMessageDto getMessages(Long chatRoomId);

    void leaveChatRoom(Long chatRoomId, String nickname);
}
