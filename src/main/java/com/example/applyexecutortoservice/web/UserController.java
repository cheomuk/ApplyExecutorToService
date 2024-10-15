package com.example.applyexecutortoservice.web;

import com.example.applyexecutortoservice.dto.user.UserRequest;
import com.example.applyexecutortoservice.dto.user.UserResponse;
import com.example.applyexecutortoservice.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
@Tag(name = "Users Controller", description = "유저 기능 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 가입 API")
    @PostMapping(value = "/signup")
    public ResponseEntity<String> signUp(UserRequest userRequest) {
        userService.signUp(userRequest);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @Operation(summary = "로그인 API")
    @PostMapping(value = "/login")
    public UserResponse login(UserRequest userRequest) {
        return userService.login(userRequest);
    }
}
