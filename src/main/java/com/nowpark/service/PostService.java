package com.nowpark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowpark.domain.Board;
import com.nowpark.domain.Post;
import com.nowpark.repository.BoardRepository;
import com.nowpark.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {

	private final PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Transactional
	public Long insert(Post post) {
		Post newPost = postRepository.save(post);
		return newPost.getId();
	}

	public List<Post> selectAll(Long boardId) {
		return postRepository.findByBoardId(boardId);
	}

	public Post select(Long boardId, Long id) {
		return postRepository.findByBoardIdAndId(boardId, id);
	}

	@Transactional
	public void delete(Long boardId, Long id) {
		postRepository.deleteByBoardIdAndId(boardId, id);
	}
}
