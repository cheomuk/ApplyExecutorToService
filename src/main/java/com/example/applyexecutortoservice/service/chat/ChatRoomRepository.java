package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatRoomRepository {

    void save(ChatRoom chatRoom);

    Page<ChatRoom> findByNickname(String nickname, Pageable pageable);

    void leave(Long chatRoomId, String nickname);

    ChatRoom findById(Long chatRoomId);

    void deleteById(Long chatRoomId);
}
