package com.clone.pinterest.service;


import com.clone.pinterest.domain.Liken;
import com.clone.pinterest.repository.LikenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikenRepository likenRepository;

    public void addLike(Long commentId) {
        Liken liken = new Liken(commentId);
        likenRepository.save(liken);
    }

    public void delete(Long commentId) {
        Liken liken = likenRepository.findByCommentId(commentId);
        likenRepository.delete(liken);
    }
}
