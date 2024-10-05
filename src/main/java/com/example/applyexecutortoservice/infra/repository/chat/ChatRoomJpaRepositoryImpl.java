package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.service.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomJpaRepositoryImpl implements ChatRoomRepository {

    private final ChatRoomJpaRepository chatRoomRepository;

    @Override
    public void save(ChatRoom chatRoom) {

    }

    @Override
    public List<ChatRoom> findByNicknameWithPaging(String nickname, int page) {
        return List.of();
    }

    @Override
    public List<ChatRoom> getChatRoomListSortedByLastMessage(String nickname, int page) {
        return List.of();
    }

    @Override
    public void leave(Long chatRoomId, String nickname) {

    }

    @Override
    public ChatRoom findById(Long chatRoomId) {
        return null;
    }

    @Override
    public void deleteById(Long chatRoomId) {

    }
}
