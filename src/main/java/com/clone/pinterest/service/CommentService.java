package com.clone.pinterest.service;


import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.dto.CommentRequestDto;
import com.clone.pinterest.repository.CommentsRepository;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentsRepository commentsRepository;
    private final PinRepository pinRepository;

    // 댓글 생성
    public Comments createComment(Long id, CommentRequestDto commentRequestDto) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO ID")
        );
        Comments comments = new Comments(commentRequestDto, pin);
        return commentsRepository.save(comments);
        
    }

    // 댓글 조회
    public List<Comments> findComment(Long id) {
        return commentsRepository.findAllByPin_PinId(id);
    }

    // 댓글 삭제
    public Long deleteComment(Long id) {
        commentsRepository.deleteById(id);
        return id;
    }

    // 댓글 수정
    @Transactional
    public Comments editComment(Long id, CommentRequestDto commentRequestDto) {
        Comments comments = commentsRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO ID")
        );
        comments.edit(commentRequestDto);
        return comments;
    }
}
