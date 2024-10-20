package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.core.exception.ErrorCode;
import com.example.applyexecutortoservice.core.exception.impl.NotFoundException;
import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.infra.repository.RedisRepository;
import com.example.applyexecutortoservice.service.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final RedisRepository redisRepository;

    @Override
    @Transactional
    public void createChatRoom(String name, List<String> userList) {
        List<User> lists = new ArrayList<>();

        for (String nickname : userList) {
            User user = userRepository.findByNickname(nickname).orElseThrow(()
                    -> new NotFoundException("없는 유저입니다.", ErrorCode.NOT_FOUND_EXCEPTION));

            lists.add(user);
        }

        ChatRoom chatRoom = new ChatRoom(null, name, lists);
        chatRoomRepository.save(chatRoom);  // DB에 채팅방 저장

        // Redis에도 새 채팅방 저장
        redisRepository.saveChatRoom(userList, chatRoom);
    }

    @Override
    @Cacheable(value = "chatRoomList", key = "#nickname", condition = "#page == 1")  // 페이지가 1이면 캐싱 처리
    public Page<ChatRoom> getChatRoomLists(String nickname, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        // 페이지가 2 이상일 경우 DB에서 조회
        if (page > 1) {
            return chatRoomRepository.findByNickname(nickname, pageable);
        }

        // Redis에서 채팅방 목록을 최신 메시지 순으로 가져옴
        Page<ChatRoom> chatRoomList = redisRepository.getChatRoomListSortedByLastMessage(nickname, pageable);

        // 캐시에 없거나 빈 값이면 DB에서 가져와 Redis에 저장
        if (chatRoomList == null || chatRoomList.isEmpty()) {
            chatRoomList = chatRoomRepository.findByNickname(nickname, pageable);
            redisRepository.saveChatRoom(nickname, chatRoomList);  // Redis에 채팅방 목록 캐싱
        }

        return chatRoomList;
    }

    @Override
    @Cacheable(value = "messages", key = "'chatRoom-' + #chatRoomId")
    public List<MessageStatusDto> getMessages(Long chatRoomId) {
        List<MessageStatusDto> messages = chatRepository.findByChatRoomId(chatRoomId);
        redisRepository.reassignToCacheMessages(messages);

        return messages;
    }

    @Override
    @Transactional
    public void leaveChatRoom(Long chatRoomId, String nickname) {
        chatRoomRepository.leave(chatRoomId, nickname);
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId);
        redisRepository.leaveChatRoom(chatRoom, nickname);

        if (chatRoom.getUserList().size() - 1 <= 1) {
            chatRepository.deleteByChatRoomId(chatRoomId);
            chatRoomRepository.deleteById(chatRoomId);
            redisRepository.removeChatRoom(chatRoomId);
        }
    }
}


