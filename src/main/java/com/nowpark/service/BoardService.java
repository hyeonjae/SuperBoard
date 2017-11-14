package com.nowpark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowpark.domain.Board;
import com.nowpark.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Transactional
	public Long insert(Board board) {
		Board newBoard = boardRepository.save(board);
		return newBoard.getId();
	}

	public List<Board> selectAll() {
		return boardRepository.findAll();
	}

	public Board select(Long id) {
		return boardRepository.findOne(id);
	}

	@Transactional
	public void delete(Long id) {
		boardRepository.delete(id);
	}
}
