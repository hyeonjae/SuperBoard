package com.nowpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowpark.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
