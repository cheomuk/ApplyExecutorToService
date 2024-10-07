package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.core.exception.ErrorCode;
import com.example.applyexecutortoservice.core.exception.impl.NotFoundException;
import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.infra.entity.*;
import com.example.applyexecutortoservice.service.chat.ChatRoomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ChatRoomJpaRepositoryImpl implements ChatRoomRepository {

    private final ChatRoomJpaRepository chatRoomRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(ChatRoom chatRoom) {
        chatRoomRepository.save(ChatRoomEntity.from(chatRoom));
    }

    @Override
    public Page<ChatRoom> findByNickname(String nickname, Pageable pageable) {
        QChatRoomEntity chatRoom = QChatRoomEntity.chatRoomEntity;
        QChatEntity chat = QChatEntity.chatEntity;
        QChatRoomUserEntity chatRoomUser = QChatRoomUserEntity.chatRoomUserEntity;
        QUserEntity user = QUserEntity.userEntity;

        // 유저가 포함된 채팅방 목록을 조회, 최근 채팅 순으로 정렬
        List<ChatRoomEntity> chatRooms = jpaQueryFactory
                .select(chatRoom)
                .from(chatRoom)
                .join(chatRoom.chatRoomUsers, chatRoomUser)
                .join(chatRoomUser.user, user)
                .join(chat.chatRoom, chatRoom)
                .where(user.nickname.eq(nickname))
                .groupBy(chatRoom.id)
                .orderBy(chat.createdDate.max().desc())  // 가장 최근 메시지 순으로 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ChatRoom> chatRoomList = chatRooms.stream()
                .map(ChatRoomEntity::toModel)
                .collect(Collectors.toList());

        long total = jpaQueryFactory
                .select(chatRoom)
                .from(chatRoom)
                .join(chatRoom.chatRoomUsers, chatRoomUser)
                .join(chatRoomUser.user, user)
                .where(user.nickname.eq(nickname))
                .fetchCount();

        return new PageImpl<>(chatRoomList, pageable, total);
    }

    @Override
    public void leave(Long chatRoomId, String nickname) {

    }

    @Override
    public ChatRoom findById(Long chatRoomId) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 방입니다.", ErrorCode.NOT_FOUND_EXCEPTION));

        return chatRoomEntity.toModel();
    }

    @Override
    public void deleteById(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }
}
