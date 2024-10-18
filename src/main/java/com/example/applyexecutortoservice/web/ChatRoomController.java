package com.example.applyexecutortoservice.web;

import com.example.applyexecutortoservice.domain.chat.ChatRoom;
import com.example.applyexecutortoservice.dto.chat.ChatRoomLeaveRequest;
import com.example.applyexecutortoservice.dto.chat.ChatRoomRequest;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.service.chat.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/room")
@Tag(name = "ChatRoom Controller", description = "채팅방 API")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Operation(summary = "채팅방 생성 API")
    @PostMapping("")
    public ResponseEntity<String> createChatRoom(@RequestBody ChatRoomRequest chatRoomRequest) {
        chatRoomService.createChatRoom(chatRoomRequest.getName(), chatRoomRequest.getUserList());
        return ResponseEntity.ok("채팅방이 생성되었습니다.");
    }

    @Operation(summary = "참여 중인 채팅방 리스트 출력 API")
    @GetMapping("")
    public Page<ChatRoom> getChatRoomLists(@RequestParam(value = "nickname") String nickname,
                                           @RequestParam(value = "page", defaultValue = "1") int page) {
        return chatRoomService.getChatRoomLists(nickname, page);
    }

    @Operation(summary = "채팅방 메시지 출력 API")
    @GetMapping("/{id}")
    public List<MessageStatusDto> getMessages(@PathVariable Long id) {
        return chatRoomService.getMessages(id);
    }

    @Operation(summary = "채팅방 나가기 API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> leaveChatRoom(@PathVariable Long id, @RequestBody ChatRoomLeaveRequest request) {
        chatRoomService.leaveChatRoom(id, request.getNickname());
        return ResponseEntity.ok("채팅방에서 퇴장했습니다.");
    }
}
