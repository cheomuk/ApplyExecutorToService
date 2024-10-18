package com.example.applyexecutortoservice.domain.user;

import com.example.applyexecutortoservice.domain.user.enums.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private String uid;
    private String nickname;
    private UserRole userRole;

    public User() {}

    @Builder
    public User(String uid, String nickname, UserRole userRole) {
        this.uid = uid;
        this.nickname = nickname;
        this.userRole = userRole;
    }
}
