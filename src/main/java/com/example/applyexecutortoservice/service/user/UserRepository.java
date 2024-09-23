package com.example.applyexecutortoservice.service.user;

import com.example.applyexecutortoservice.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByNickname(String nickname);

    void save(User user);

    boolean existsByNickname(String nickname);
}
