package com.nowpark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowpark.domain.Board;
import com.nowpark.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByBoardId(Long boardId);

	Post findByBoardIdAndId(Long boardId, Long id);

	void deleteByBoardIdAndId(Long boardId, Long id);
}
