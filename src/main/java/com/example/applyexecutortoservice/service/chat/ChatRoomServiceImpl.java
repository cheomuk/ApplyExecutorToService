package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.ChatMessageDto;
import com.example.applyexecutortoservice.infra.repository.RedisRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Builder
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final RedisRepository redisRepository;

    @Override
    public void createChatRoom(List<String> userList) {

    }

    @Override
    public List<ChatRoom> getChatRoomLists() {
        return List.of();
    }

    @Override
    public ChatMessageDto getMessages(Long chatRoomId) {
        return null;
    }

    @Override
    public void leaveChatRoom(Long chatRoomId, String nickname) {

    }
}
