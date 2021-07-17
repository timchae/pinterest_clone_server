package com.clone.pinterest.service;


import com.clone.pinterest.domain.Liken;
import com.clone.pinterest.domain.User;
import com.clone.pinterest.exception.ApiRequestException;
import com.clone.pinterest.repository.LikenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikenRepository likenRepository;

    public void addLike(Long commentId, User user) {
        Long userId = user.getUserId();
        boolean exist = likenRepository.existsByCommentIdAndUserId(commentId,userId);
        if(exist){
            throw new ApiRequestException("이미 좋아요를 눌렀습니다.");
        }
        Liken liken = new Liken(commentId, user.getUserId());
        likenRepository.save(liken);
    }

    public void delete(Long commentId, User user) {
        Liken liken = likenRepository.findByCommentId(commentId);
        if(!liken.getUserId().equals(user.getUserId())){
            throw new ApiRequestException("권한이 없습니다.");
        }
        likenRepository.delete(liken);
    }
}
