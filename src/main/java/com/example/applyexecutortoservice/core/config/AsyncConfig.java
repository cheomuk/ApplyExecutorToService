package com.example.applyexecutortoservice.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    @Primary
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);  // 기본 스레드 수
        executor.setMaxPoolSize(5);   // 최대 스레드 수
        executor.setQueueCapacity(50); // 대기열 크기
        executor.setThreadNamePrefix("AsyncThread-");   // 스레드 이름에 접두사를 지정함.
        executor.initialize();

        return executor;
    }
}
