package com.nowpark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nowpark.domain.Board;
import com.nowpark.domain.User;
import com.nowpark.service.BoardService;
import com.nowpark.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/boards")
public class BoardController {

	private final BoardService boardService;

	private final UserService userService;

	@Autowired
	public BoardController(BoardService boardService, UserService userService) {
		this.boardService = boardService;
		this.userService = userService;
	}

	@PostMapping(value = "")
	public Board create(@RequestBody Board board, @RequestHeader("userId") Long userId) {
		User user = userService.select(userId);
		board.setOwner(user);
		Long id = boardService.insert(board);
		return Board.builder().id(id).build();
	}

	@GetMapping(value = "")
	public List<Board> showAll() {
		return boardService.selectAll();
	}

	@GetMapping(value = "/{id}")
	public Board show(@PathVariable Long id) {
		return boardService.select(id);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		boardService.delete(id);
	}
}
