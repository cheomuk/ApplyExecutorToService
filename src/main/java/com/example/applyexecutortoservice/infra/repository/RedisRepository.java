package com.example.applyexecutortoservice.infra.repository;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void reassignToCacheMessages(List<MessageStatusDto> messageStatusDto) {
        for (MessageStatusDto message : messageStatusDto) {
            redisTemplate.opsForList().rightPush("chatRoom-" + message.getChatRoomId(), message);
        }
    }

    public void saveMessage(MessageStatusDto messageStatusDto) {
        redisTemplate.opsForList().rightPush("chatRoom-" + messageStatusDto.getChatRoomId(), messageStatusDto);
    }

    public void saveChatRoom(String nickname, List<ChatRoom> chatRoomlist) {
        for (ChatRoom chatRoom : chatRoomlist) {
            redisTemplate.opsForList().leftPush("chatRoomList-" + nickname, chatRoom);
        }
    }

    public void saveChatRoom(List<String> lists, ChatRoom chatRoom) {
        for (String nickname : lists) {
            redisTemplate.opsForList().rightPushAll("chatRoomList-" + nickname, chatRoom);
        }
    }

    // 최신 메시지 순으로 채팅방 리스트를 가져오는 메서드
    public List<ChatRoom> getChatRoomListSortedByLastMessage(String nickname) {
        // Redis에서 Object로 값을 받아온 후 ChatRoom으로 변환
        List<Object> chatRoomObjects = redisTemplate.opsForList().range("chatRoomList-" + nickname, 0, -1);

        assert chatRoomObjects != null;

        return chatRoomObjects.stream()
                .map(object -> (ChatRoom) object)
                .collect(Collectors.toList());
    }

    public List<MessageStatusDto> getMessages(Long chatRoomId) {
        List<Object> messages = redisTemplate.opsForList().range("chatRoom-" + chatRoomId, 0, -1);

        assert messages != null;

        return messages.stream()
                .map(object -> (MessageStatusDto) object)
                .collect(Collectors.toList());
    }

    public void leaveChatRoom(ChatRoom chatRoom, String nickname) {
        redisTemplate.opsForList().remove("chatRoomList-" + nickname, 1, chatRoom);
    }

    public void removeChatRoom(Long chatRoomId) {
        redisTemplate.delete("chatRoom-" + chatRoomId);
    }
}

