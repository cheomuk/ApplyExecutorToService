package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;

import java.util.List;

public interface ChatRoomRepository {

    void save(ChatRoom chatRoom);

    List<ChatRoom> findByNicknameWithPaging(String nickname, int page);

    List<ChatRoom> getChatRoomListSortedByLastMessage(String nickname, int page);

    void leave(Long chatRoomId, String nickname);

    ChatRoom findById(Long chatRoomId);

    void deleteById(Long chatRoomId);
}
