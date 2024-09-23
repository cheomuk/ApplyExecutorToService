package com.example.applyexecutortoservice.infra.repository.user;

import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.infra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepositoty extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNickname(String nickname);

    void save(User user);

    boolean existsByNickname(String nickname);
}
