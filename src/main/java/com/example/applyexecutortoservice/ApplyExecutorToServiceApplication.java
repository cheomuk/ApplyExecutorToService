package com.example.applyexecutortoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApplyExecutorToServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplyExecutorToServiceApplication.class, args);
	}

}
