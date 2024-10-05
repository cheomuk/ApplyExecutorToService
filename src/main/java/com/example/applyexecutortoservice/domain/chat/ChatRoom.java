package com.example.applyexecutortoservice.domain.chat;

import com.example.applyexecutortoservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatRoom {

    private final Long id;
    private String name;
    private List<User> userList;

    @Builder
    public ChatRoom(Long id, String name, List<User> userList) {
        this.id = id;
        this.name = name;
        this.userList = userList;
    }

    public void create(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }
}
