package com.clone.pinterest.service;

import com.clone.pinterest.domain.Board;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import com.clone.pinterest.dto.request.BoardRequestDto;
import com.clone.pinterest.dto.response.BoardResponseDto;
import com.clone.pinterest.repository.BoardRepository;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PinRepository pinRepository;

    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user){
        Board board = new Board(boardRequestDto, user);
        board = boardRepository.save(board);
        System.out.println("boardRequestDto = " + boardRequestDto + ", user = " + user);
        return new BoardResponseDto(
                board.getBoardId(),
                board.getBoardTitle()
        );
    }

    public List<BoardResponseDto> getBoard(User user) {

        List<Board> boards = boardRepository.findAllByUser_UserId(user.getUserId());
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        for (Board board : boards) {

            BoardResponseDto responseDto = new BoardResponseDto();
            Long pinNum = pinRepository.countAllByBoard_BoardId(board.getBoardId());
            List<String> img = new ArrayList<>();
            List<Pin> pins = pinRepository.findTop5ByBoard_BoardId(board.getBoardId());
            for (Pin pin : pins) {
                img.add(pin.getPinImage());
            }
            responseDto.setBoardId(board.getBoardId());
            responseDto.setPinNum(pinNum);
            responseDto.setPinImage(img);
            responseDto.setBoardTitle(board.getBoardTitle());


            boardResponseDtos.add(responseDto);
        }

        return boardResponseDtos;
    }

}
