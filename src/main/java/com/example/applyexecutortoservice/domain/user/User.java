package com.example.applyexecutortoservice.domain.user;

import com.example.applyexecutortoservice.domain.user.enums.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final String uid;
    private final String nickname;
    private final UserRole userRole;

    @Builder
    public User(String uid, String nickname, UserRole userRole) {
        this.uid = uid;
        this.nickname = nickname;
        this.userRole = userRole;
    }
}
