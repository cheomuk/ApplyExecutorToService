package com.example.applyexecutortoservice.web;

import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.service.chat.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Chatting Controller", description = "채팅 API")
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @Operation(summary = "실시간 채팅 저장 API")
    @MessageMapping("/chat")
    public void send(MessageStatusDto messageStatusDto) {
        String str = chatService.saveMessage(messageStatusDto);

        if (str.equals("ok")) {
            messagingTemplate.convertAndSend("/sub/" + messageStatusDto.getChatRoomId(), messageStatusDto);
        } else {
            this.sendErrorMessage(str, messageStatusDto.getChatRoomId());
        }
    }

    private void sendErrorMessage(String errorMessage, Long roomId) {
        // ERROR 프레임 생성
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
        accessor.setMessage(errorMessage);

        // 추가 헤더 설정
        accessor.setHeader("content-type", "application/json");

        // ERROR 프레임 전송
        messagingTemplate.convertAndSend("/sub/" + roomId, errorMessage, accessor.getMessageHeaders());
    }
}
