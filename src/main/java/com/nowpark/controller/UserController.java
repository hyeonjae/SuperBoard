package com.nowpark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nowpark.domain.Board;
import com.nowpark.domain.User;
import com.nowpark.service.BoardService;
import com.nowpark.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "")
	public User create(@RequestBody User user) {
		Long id = userService.insert(user);
		return User.builder()
				.id(id)
				.build();
	}

	@GetMapping(value = "")
	public List<User> showAll() {
		return userService.selectAll();
	}

	@GetMapping(value = "/{id}")
	public User show(@PathVariable Long id) {
		return userService.select(id);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
}
