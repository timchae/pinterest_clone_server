package com.clone.pinterest.controller;


import com.clone.pinterest.security.UserDetailsImpl;
import com.clone.pinterest.service.LikeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 좋아요 추가
    @ApiOperation(value = "댓글에 좋아요 추가")
    @PostMapping("/like/{commentid}")
    public void addLike(@PathVariable(name = "commentid") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.addLike(id, userDetails.getUser());
    }

    // 좋아요 삭제
    @ApiOperation(value = "댓글에 좋아요 삭제")
    @DeleteMapping("/like/{commentid}")
    public void deleteLike(@PathVariable(name = "commentid") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.delete(id, userDetails.getUser());
    }
}
