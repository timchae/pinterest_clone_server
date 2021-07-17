package com.clone.pinterest.controller;

import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.dto.request.CommentRequestDto;
import com.clone.pinterest.dto.response.CommentResponseDto;
import com.clone.pinterest.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // pin의 댓글 가져오기 api
    @GetMapping("/pin/comment/{id}")
    public List<CommentResponseDto> pinComment(@PathVariable Long id){
        return commentService.findComment(id);
    }


    // pin 댓글 작성
    @PostMapping("/user/pin/comment/{id}")
    public Comments createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(id, commentRequestDto);
    }

    // pin 댓글 삭제
    @DeleteMapping("/user/pin/comment/{id}")
    public Long deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }

    // pin 댓글 수정
    @PutMapping("/user/pin/comment/{id]")
    public Comments editComment(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto){
        return commentService.editComment(id, commentRequestDto);
    }


}
