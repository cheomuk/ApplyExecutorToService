package com.example.applyexecutortoservice.controller;


import com.example.applyexecutortoservice.dto.user.UserRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUp(@Autowired DataSource dataSource) {
        // 스프링 컨텍스트가 로드된 후, 한 번만 실행되는 초기화 블록
        ResourceDatabasePopulator pt = new ResourceDatabasePopulator();
        pt.addScript(new ClassPathResource("/sql/controller-test-data.sql"));
        pt.execute(dataSource);
    }

    @AfterAll
    public static void cleanUp(@Autowired DataSource dataSource) {
        // 모든 테스트가 완료된 후, 데이터 정리
        ResourceDatabasePopulator pt = new ResourceDatabasePopulator();
        pt.addScript(new ClassPathResource("/sql/delete-all-data.sql"));
        pt.execute(dataSource);
    }

    @Test
    void 회원_가입을_진행할_수_있다() throws Exception {
        // given
        UserRequest userRequest = UserRequest.builder()
                .nickname("Test-User1")
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("nickname", userRequest.getNickname())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void 사용자는_로그인을_진행할_수_있다() throws Exception {
        // given
        UserRequest userRequest = UserRequest.builder()
                .nickname("Test-User")
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("nickname", userRequest.getNickname())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("Test-User"))
                .andExpect(jsonPath("$.responseCode").value("로그인에 성공했습니다."));
    }
}
