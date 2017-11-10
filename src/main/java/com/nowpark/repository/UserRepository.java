package com.nowpark.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nowpark.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByRegisteredAtAfter(Date date);

	@Query("SELECT u FROM User u WHERE u.name = ?1")
	List<User> findByCustomQuery(String name);
}
