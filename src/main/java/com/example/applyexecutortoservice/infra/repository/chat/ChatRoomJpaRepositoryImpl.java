package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.service.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRoomJpaRepositoryImpl implements ChatRoomRepository {

    private final ChatRoomJpaRepository chatRoomRepository;
}
