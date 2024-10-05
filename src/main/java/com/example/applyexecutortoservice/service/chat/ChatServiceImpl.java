package com.example.applyexecutortoservice.service.chat;

import com.example.applyexecutortoservice.core.utils.Logger;
import com.example.applyexecutortoservice.dto.chat.MessageStatusDto;
import com.example.applyexecutortoservice.infra.repository.RedisRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Builder
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final RedisRepository redisRepository;
    private final ThreadPoolTaskExecutor taskExecutor;

    @Override
    @Transactional
    public String saveMessage(MessageStatusDto messageStatusDto) {
        try {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                // 메시지를 DB에 저장
                chatRepository.save(messageStatusDto);
                // Redis에 메시지 캐싱 (DB 저장 후 비동기로 처리)
                redisRepository.saveMessage(messageStatusDto);
            }, taskExecutor);

            future.join();  // 비동기 작업이 완료될 때까지 기다림
            return "ok";
        } catch (Exception e) {
            Logger.log(e);  // 예외 로그 출력
            return "error";
        }
    }
}
