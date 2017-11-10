package com.nowpark.service;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void insertData() {
		userRepository.save(User.builder().name("nowpark").email("hyeonjae2010@gmail.com").registeredAt(Date.from(ZonedDateTime.now().toInstant())).build());
		userRepository.save(User.builder().name("yato.park").email("yato@gmail.com").registeredAt(Date.from(ZonedDateTime.now().toInstant())).build());
		userRepository.save(User.builder().name("sejoong.park").email("conquerorj@gmail.com").registeredAt(Date.from(ZonedDateTime.now().toInstant())).build());
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
}
