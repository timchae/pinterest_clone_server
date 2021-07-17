package com.clone.pinterest.controller;


import com.clone.pinterest.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 좋아요 추가
    @PostMapping("/user/like/{id}")
    public void addLike(@PathVariable Long id){
        likeService.addLike(id);
    }

    // 좋아요 삭제
    @DeleteMapping("/user/like/{id}")
    public void deleteLike(@PathVariable Long id){
        likeService.delete(id);
    }
}
