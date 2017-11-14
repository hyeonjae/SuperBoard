package com.nowpark.service;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowpark.domain.Post;
import com.nowpark.domain.User;
import com.nowpark.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public Long insert(User user) {
		user.setRegisteredAt(Date.from(ZonedDateTime.now().toInstant()));
		User newUser = userRepository.save(user);
		return newUser.getId();
	}

	public void insertData() {
		userRepository.save(User.builder().name("aaa").email("aaa@gmail.com").registeredAt(Date.from(ZonedDateTime.now().toInstant())).build());
		userRepository.save(User.builder().name("bbb").email("bbb@gmail.com").registeredAt(Date.from(ZonedDateTime.now().toInstant())).build());
		userRepository.save(User.builder().name("ccc").email("ccc@gmail.com").registeredAt(Date.from(ZonedDateTime.now().toInstant())).build());
	}

	public List<User> selectAll() {
		return userRepository.findAll();
	}

	public User select(Long id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public void delete(Long id) {
		userRepository.delete(id);
	}
}
