package com.example.applyexecutortoservice.infra.repository.user;

import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.infra.entity.UserEntity;
import com.example.applyexecutortoservice.service.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserJpaRepositoryImpl implements UserRepository {

    private final UserJpaRepositoty userRepository;

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname).map(UserEntity::toModel);
    }

    @Override
    public void save(User user) {
        userRepository.save(UserEntity.from(user));
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
