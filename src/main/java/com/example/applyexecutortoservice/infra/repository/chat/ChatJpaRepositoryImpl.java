package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.domain.chat.Chat;
import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.infra.entity.ChatEntity;
import com.example.applyexecutortoservice.infra.entity.QChatEntity;
import com.example.applyexecutortoservice.infra.entity.QChatRoomEntity;
import com.example.applyexecutortoservice.service.chat.ChatRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatJpaRepositoryImpl implements ChatRepository {

    private final ChatJpaRepository chatRepository;
    private final ChatRoomJpaRepository chatRoomRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(MessageStatusDto messageStatusDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(messageStatusDto.getChatRoomId()).get().toModel();
        Chat chat = Chat.builder()
                .id(null)
                .chatRoom(chatRoom)
                .sender(messageStatusDto.getSender())
                .message(messageStatusDto.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        chatRepository.save(ChatEntity.from(chat));
    }

    @Override
    public List<MessageStatusDto> findByChatRoomId(Long chatRoomId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                MessageStatusDto.class,
                                QChatRoomEntity.chatRoomEntity.id.as("chatRoomId"),
                                QChatEntity.chatEntity.sender,
                                QChatEntity.chatEntity.message,
                                QChatEntity.chatEntity.createdDate.as("dateTime")
                        )
                )
                .from(QChatEntity.chatEntity)
                .innerJoin(QChatRoomEntity.chatRoomEntity)
                .on(QChatRoomEntity.chatRoomEntity.id.eq(chatRoomId))
                .orderBy(QChatEntity.chatEntity.createdDate.desc())
                .fetch();
    }

    @Override
    public void deleteByChatRoomId(Long chatRoomId) {
        chatRepository.deleteByChatRoomId(chatRoomId);
    }
}
