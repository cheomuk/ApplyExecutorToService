package com.example.applyexecutortoservice.service.user;

import com.example.applyexecutortoservice.dto.user.UserRequest;
import com.example.applyexecutortoservice.dto.user.UserResponse;

public interface UserService {

    void signUp(UserRequest userRequest);

    UserResponse login(UserRequest userRequest);
}
