package com.clone.pinterest.service;

import com.clone.pinterest.domain.Board;
import com.clone.pinterest.dto.request.BoardRequestDto;
import com.clone.pinterest.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board createBoard(BoardRequestDto boardRequestDto){
        Board board = new Board(boardRequestDto);
        return boardRepository.save(board);
    }


}