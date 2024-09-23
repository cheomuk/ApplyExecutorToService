package com.example.applyexecutortoservice.service.user;

import com.example.applyexecutortoservice.core.exception.ErrorCode;
import com.example.applyexecutortoservice.core.exception.impl.UnAuthorizedException;
import com.example.applyexecutortoservice.dto.user.UserRequest;
import com.example.applyexecutortoservice.dto.user.UserResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Builder
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void signUp(UserRequest userRequest) {
        if (userRepository.existsByNickname(userRequest.getNickname())) {
            throw new UnAuthorizedException("사용 중인 닉네임입니다.", ErrorCode.DUPLICATED_EXCEPTION);
        }

        userRepository.save(userRequest.toDomain());
    }

    @Override
    public UserResponse login(UserRequest userRequest) {
        if (!userRepository.existsByNickname(userRequest.getNickname())) {
            throw new UnAuthorizedException("없는 회원 정보입니다.", ErrorCode.ACCESS_DENIED_EXCEPTION);
        }

        return new UserResponse(userRequest.getNickname(), "로그인에 성공했습니다.");
    }
}
