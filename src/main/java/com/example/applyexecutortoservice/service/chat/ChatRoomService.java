package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChatRoomService {

    void createChatRoom(String name, List<String> userList);

    Page<ChatRoom> getChatRoomLists(String nickname, int page);

    List<MessageStatusDto> getMessages(Long chatRoomId);

    void leaveChatRoom(Long chatRoomId, String nickname);
}
