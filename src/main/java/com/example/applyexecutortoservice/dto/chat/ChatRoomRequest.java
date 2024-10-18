package com.example.applyexecutortoservice.dto.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomRequest {

    @Schema(description = "채팅방 이름")
    private String name;

    @Schema(description = "채팅 참여 유저 리스트")
    private List<String> userList;
}
