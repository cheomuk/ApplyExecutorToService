package com.example.applyexecutortoservice.service;

import com.example.applyexecutortoservice.core.exception.impl.UnAuthorizedException;
import com.example.applyexecutortoservice.domain.user.User;
import com.example.applyexecutortoservice.domain.user.enums.UserRole;
import com.example.applyexecutortoservice.dto.user.UserRequest;
import com.example.applyexecutortoservice.dto.user.UserResponse;
import com.example.applyexecutortoservice.mock.FakeUserRepository;
import com.example.applyexecutortoservice.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserServiceTest {

    private UserServiceImpl userService;
    private FakeUserRepository fakeUserRepository;

    @BeforeEach
    void init() {
        fakeUserRepository = new FakeUserRepository();

        this.userService = UserServiceImpl.builder()
                .userRepository(fakeUserRepository)
                .build();

        User user = User.builder()
                .uid("")
                .nickname("Test-User")
                .userRole(UserRole.USER)
                .build();

        fakeUserRepository.save(user);
    }

    @Test
    void login_을_성공적으로_마치면_성공_메시지를_반환한다() {
        // given
        String nickname = "Test-User";
        UserRequest request = new UserRequest(nickname);

        // when
        UserResponse userResponse = userService.login(request);

        // then
        assertThat(userResponse.getNickname()).isEqualTo("Test-User");
        assertThat(userResponse.getResponseMessage()).isEqualTo("로그인에 성공했습니다.");
    }

    @Test
    void 회원이_아닐_경우_throw_익셉션을_반환한다() {
        // given
        String nickname = "Test-User-1";
        UserRequest request = new UserRequest(nickname);

        // when
        // then
        assertThatThrownBy(() -> {
            userService.login(request);
        }).isInstanceOf(UnAuthorizedException.class)
                .hasMessage("없는 회원 정보입니다.");
    }

    @Test
    void 회원_가입을_진행할_수_있다() {
        // given
        UserRequest request = UserRequest.builder()
                .nickname("Test-User1")
                .build();

        // when
        userService.signUp(request);

        // then
        assertThat(fakeUserRepository.findByNickname(request.getNickname())).isPresent();
    }

    @Test
    void 이미_회원인_경우_회원_가입을_진행하면_401_에러를_반환한다() {
        // given
        UserRequest request = UserRequest.builder()
                .nickname("Test-User")
                .build();

        // when
        // then
        assertThatThrownBy(() -> {
            userService.signUp(request);
        }).isInstanceOf(UnAuthorizedException.class)
                .hasMessage("사용 중인 닉네임입니다.");
    }
}
