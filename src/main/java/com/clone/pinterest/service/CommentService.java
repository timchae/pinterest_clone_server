package com.clone.pinterest.service;


import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.dto.CommentRequestDto;
import com.clone.pinterest.dto.CommentResponseDto;
import com.clone.pinterest.repository.CommentsRepository;
import com.clone.pinterest.repository.LikenRepository;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentsRepository commentsRepository;
    private final PinRepository pinRepository;
    private final LikenRepository likenRepository;

    // 댓글 생성
    public Comments createComment(Long id, CommentRequestDto commentRequestDto) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO ID")
        );
        Comments comments = new Comments(commentRequestDto, pin);
        return commentsRepository.save(comments);
        
    }

    // 댓글 조회
    public List<CommentResponseDto> findComment(Long id) {
        List<Comments> comments = commentsRepository.findAllByPin_PinId(id);
        List<CommentResponseDto> comments1 = new ArrayList<>();
        for(Comments comment: comments){
            Long commentId = comment.getCommentId();
            Long count = likenRepository.countByCommentId(commentId);
            boolean liken = likenRepository.existsByCommentId(commentId);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment,count,liken);
            comments1.add(commentResponseDto);
        }
        return comments1;
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
