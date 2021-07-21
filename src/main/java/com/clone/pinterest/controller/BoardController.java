package com.clone.pinterest.controller;


import com.clone.pinterest.domain.Board;
import com.clone.pinterest.dto.request.BoardRequestDto;
import com.clone.pinterest.dto.response.BoardResponseDto;
import com.clone.pinterest.security.UserDetailsImpl;
import com.clone.pinterest.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 보드 생성
    @ApiOperation(value = "보드 생성")
    @PostMapping("/board")
    public Board createBoard(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.createBoard(boardRequestDto);
    }

    // 보드 가져오기
    @ApiOperation(value = "보드 가져오기")
    @GetMapping("/board")
    public List<BoardResponseDto> getBoard(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.getBoard(userDetails.getUser());
    }

    //보드에 핀 추가


}
