package com.nowpark.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nowpark.domain.Board;
import com.nowpark.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/boards")
public class BoardController {

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@PostMapping(value = "")
	public Board create(@RequestBody Board board) {

		Long id = boardService.insert(board);
		return Board.builder().id(id).build();
	}

	@GetMapping(value = "")
	public List<Board> showAll() {

		return boardService.selectAll();
	}

	@GetMapping(value = "/{id}")
	public Board show(@PathVariable Long id) {

		Board board = boardService.select(id);

		return board;
	}
}
