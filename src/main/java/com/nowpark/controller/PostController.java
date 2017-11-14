package com.nowpark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nowpark.domain.Board;
import com.nowpark.domain.Post;
import com.nowpark.service.BoardService;
import com.nowpark.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/boards/{boardId}/posts")
public class PostController {

	private final BoardService boardService;

	private final PostService postService;

	@Autowired
	public PostController(BoardService boardService, PostService postService) {
		this.boardService = boardService;
		this.postService = postService;
	}

	@PostMapping(value = "")
	public Post create(@PathVariable("boardId") Long boardId, @RequestBody Post post) {
		Board board = boardService.select(boardId);
		post.setBoard(board);
		Long id = postService.insert(post);
		return Post.builder().id(id).build();
	}

	@GetMapping(value = "")
	public List<Post> showAll(@PathVariable("boardId") Long boardId) {
		return postService.selectAll(boardId);
	}

	@GetMapping(value = "/{id}")
	public Post show(@PathVariable("boardId") Long boardId, @PathVariable Long id) {
		return postService.select(boardId, id);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("boardId") Long boardId, @PathVariable Long id) {
		postService.delete(boardId, id);
	}
}
