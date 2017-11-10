package com.nowpark;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.nowpark.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SuperBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperBoardApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(UserService userService) {
		return args -> {
			log.info("Create Data...");
//			userService.insertData();
			log.info("findAll()...");
			userService.findAll().forEach(entry -> log.info(entry.toString()));
		};
	}

//	@Scheduled(fixedDelay = 500L)
//	public void sayHello() {
//		log.info("hello");
//	}
//
//	@Scheduled(fixedDelay = 501L)
//	public void sayGoodBye() {
//		log.info("goodbye");
//	}
//
//	@Scheduled(fixedDelay = 499L)
//	public void sayHi() {
//		log.info("hi");
//	}
}
