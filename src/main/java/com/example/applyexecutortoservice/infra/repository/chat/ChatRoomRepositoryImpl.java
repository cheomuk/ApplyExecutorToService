package com.example.applyexecutortoservice.infra.repository.chat;

import com.example.applyexecutortoservice.core.exception.ErrorCode;
import com.example.applyexecutortoservice.core.exception.impl.NotFoundException;
import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.domain.chat.ChatRoomUser;
import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.infra.entity.*;
import com.example.applyexecutortoservice.service.chat.ChatRoomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

    private final ChatRoomJpaRepository chatRoomRepository;
    private final ChatRoomUserJpaRepository chatRoomUserRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(ChatRoom chatRoom) {
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.from(chatRoom);
        chatRoomRepository.save(chatRoomEntity);

        chatRoom.getUserList().forEach(user -> {
            ChatRoomUser chatRoomUser = new ChatRoomUser(null, chatRoomEntity.toModel(), user);
            chatRoomUserRepository.save(ChatRoomUserEntity.from(chatRoomUser));
        });
    }

    @Override
    public Page<ChatRoom> findByNickname(String nickname, Pageable pageable) {
        QChatRoomEntity chatRoom = QChatRoomEntity.chatRoomEntity;
        QChatRoomUserEntity chatRoomUser = QChatRoomUserEntity.chatRoomUserEntity;
        QUserEntity user = QUserEntity.userEntity;

        List<ChatRoomEntity> chatRooms = jpaQueryFactory
                .select(chatRoom)
                .from(chatRoom)
                .leftJoin(chatRoom.chatRoomUsers, chatRoomUser)
                .leftJoin(chatRoomUser.user, user)
                .where(user.nickname.eq(nickname))
                .distinct()
                .groupBy(chatRoom.id)
                .orderBy(chatRoom.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ChatRoom> chatRoomList = chatRooms.stream()
                .map(ChatRoomEntity::toModel)
                .collect(Collectors.toList());

        Long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(chatRoom.countDistinct())
                        .from(chatRoom)
                        .leftJoin(chatRoom.chatRoomUsers, chatRoomUser)
                        .leftJoin(chatRoomUser.user, user)
                        .where(user.nickname.eq(nickname))
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(chatRoomList, pageable, total);
    }


    @Override
    public void leave(Long chatRoomId, String nickname) {
        chatRoomUserRepository.deleteByChatRoomIdAndUserNickname(chatRoomId, nickname);
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
