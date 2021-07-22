package com.clone.pinterest.controller;

import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.dto.request.CommentRequestDto;
import com.clone.pinterest.security.UserDetailsImpl;
import com.clone.pinterest.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // pin의 댓글 가져오기 api
    @ApiOperation(value = "핀의 댓글 가져오지")
    @GetMapping("/pin/comment/{pinid}")
    public Page<Comments> pinComment(@PathVariable(name = "pinid") Long id,
                                     @RequestParam("page") int page,
                                     @RequestParam("size") int size,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        page = page - 1;
        return commentService.findComment(page, size,id, userDetails.getUser());
    }


    // pin 댓글 작성
    @ApiOperation(value = "핀에 댓글 작성")
    @PostMapping("/pin/comment/{pinid}")
    public Comments createComment(@PathVariable(name = "pinid") Long id,
                                  @RequestBody CommentRequestDto commentRequestDto,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails){

        return commentService.createComment(id, commentRequestDto, userDetails.getUser());
    }

    // pin 댓글 삭제
    @ApiOperation(value = "핀 댓글 삭제")
    @DeleteMapping("/pin/comment/{commentid}")
    public Long deleteComment(@PathVariable(name = "commentid") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails.getUser());
    }

    // pin 댓글 수정
    @ApiOperation(value = "핀 댓글 수정")
    @PutMapping("/pin/comment/{commentid}")
    public Comments editComment(@PathVariable(name = "commentid") Long id,@RequestBody CommentRequestDto commentRequestDto){
        return commentService.editComment(id, commentRequestDto);
    }


}
