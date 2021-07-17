package com.clone.pinterest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    // pin 댓글 작성
    @PostMapping("/user/pin/comment/{id}")
    public void createComment(@PathVariable Long id){
    }
}
