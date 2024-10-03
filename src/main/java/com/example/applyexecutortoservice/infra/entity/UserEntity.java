package com.example.applyexecutortoservice.infra.entity;

import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

    /*
        처음 입장 후 닉네임만 입력하면 바로 채팅을 사용할 수 있도록 설계.
        패스워드 및 토큰과 같은 요소는 사용하지 않을 예정.
        재접속 시 닉네임을 입력하면 이전 채팅 목록들을 불러옴.
        멀티스레딩 테스트 목적의 프로젝트이므로 보안은 생략함.
     */

    @Id
    private String uid;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<ChatRoomUserEntity> chatRoomUsers = new ArrayList<>();

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.uid = user.getUid();
        userEntity.nickname = user.getNickname();
        userEntity.userRole = user.getUserRole();

        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .uid(uid)
                .nickname(nickname)
                .userRole(userRole)
                .build();
    }
}
