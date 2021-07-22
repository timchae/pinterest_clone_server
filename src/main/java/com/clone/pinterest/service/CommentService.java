package com.clone.pinterest.service;


import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import com.clone.pinterest.dto.request.CommentRequestDto;
import com.clone.pinterest.dto.response.CommentResponseDto;
import com.clone.pinterest.exception.ApiRequestException;
import com.clone.pinterest.repository.CommentsRepository;
import com.clone.pinterest.repository.LikenRepository;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Comments createComment(Long id, CommentRequestDto commentRequestDto, User user) {
        pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO ID")
        );
        Comments comments = new Comments(commentRequestDto, id, user);
        return commentsRepository.save(comments);
        
    }

    // 댓글 조회
    public Page<Comments> findComment(int page, int size, Long id, User user) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comments> comments = commentsRepository.findAllByPinId(id,pageable);
        Long userId = user.getUserId();
        for(Comments comment: comments){
            Long commentId = comment.getCommentId();
            Long count = likenRepository.countByCommentId(commentId);
            boolean liken = likenRepository.existsByCommentIdAndUserId(commentId, userId);
            comment.page(count,liken);
        }
        return comments;
    }

    // 댓글 삭제
    @Transactional
    public Long deleteComment(Long id, User user) {
        Comments comments = commentsRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("삭제할 수 없습니다.")
        );
        if(!comments.getUser().getUserId().equals(user.getUserId())){
            throw new ApiRequestException("삭제할 권한이 없습니다.");
        }

        likenRepository.deleteAllByCommentId(id);
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
