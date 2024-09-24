package com.example.applyexecutortoservice.mock;

import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.service.user.UserRepository;

import java.util.*;

public class FakeUserRepository implements UserRepository {

    private final List<User> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<User> findByNickname(String nickname) {
        return data.stream().filter(item -> item.getNickname().equals(nickname)).findAny();
    }

    @Override
    public void save(User user) {
        if (user.getUid() == null) {
            User newUser = User.builder()
                    .uid(String.valueOf(UUID.randomUUID()))
                    .nickname(user.getNickname())
                    .userRole(user.getUserRole())
                    .build();

            data.add(newUser);
        } else {
            data.removeIf(item -> Objects.equals(item.getNickname(), user.getNickname()));

            data.add(user);
        }
    }

    @Override
    public boolean existsByNickname(String nickname) {
        Optional<User> value = data.stream()
                .filter(user -> user.getNickname().equals(nickname))
                .findAny();

        return value.isPresent();
    }
}
