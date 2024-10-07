package com.example.applyexecutortoservice.infra.repository;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public void saveChatRoom(String nickname, Page<ChatRoom> chatRoomList) {
        for (ChatRoom chatRoom : chatRoomList) {
            redisTemplate.opsForList().leftPush("chatRoomList-" + nickname, chatRoom);
        }
    }

    public void saveChatRoom(List<String> lists, ChatRoom chatRoom) {
        for (String nickname : lists) {
            redisTemplate.opsForList().rightPushAll("chatRoomList-" + nickname, chatRoom);
        }
    }

    public Page<ChatRoom> getChatRoomListSortedByLastMessage(String nickname, Pageable pageable) {
        List<Object> chatRoomObjects = redisTemplate.opsForList().range("chatRoomList-" + nickname, 0, -1);

        assert chatRoomObjects != null;

        List<ChatRoom> chatRooms = chatRoomObjects.stream()
                .map(object -> (ChatRoom) object)
                .collect(Collectors.toList());

        // 시작 인덱스 계산
        int start = (int) pageable.getOffset();
        // 끝 인덱스 계산 (마지막 페이지에서는 리스트 크기와 맞추기)
        int end = Math.min((start + pageable.getPageSize()), chatRooms.size());

        // 리스트를 Page로 변환
        List<ChatRoom> pageContent = chatRooms.subList(start, end);

        return new PageImpl<>(pageContent, pageable, chatRooms.size());
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

