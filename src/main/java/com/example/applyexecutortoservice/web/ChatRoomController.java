package com.example.applyexecutortoservice.web;

import com.example.applyexecutortoservice.service.chat.ChatRoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "ChatRoom Controller", description = "채팅방 API")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
}
