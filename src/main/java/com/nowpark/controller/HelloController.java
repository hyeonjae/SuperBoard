package com.nowpark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {
	@GetMapping("/")
	private String index() {
		return "{\"message\": \"index\"}";
	}

	@GetMapping("/ping")
	private String ping() {
		return "{\"message\": \"ping\"}";
	}
}
