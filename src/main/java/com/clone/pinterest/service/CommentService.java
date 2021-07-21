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
    public List<CommentResponseDto> findComment(Long id, User user) {
        List<Comments> comments = commentsRepository.findAllByPinId(id);
        List<CommentResponseDto> comments1 = new ArrayList<>();
        Long userId = user.getUserId();
        for(Comments comment: comments){
            Long commentId = comment.getCommentId();
            Long count = likenRepository.countByCommentId(commentId);
            boolean liken = likenRepository.existsByCommentIdAndUserId(commentId, userId);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment,id,count,liken);
            comments1.add(commentResponseDto);
        }
        return comments1;
    }

    // 댓글 삭제
    public Long deleteComment(Long id, User user) {
        Comments comments = commentsRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("삭제할 수 없습니다.")
        );
        if(!comments.getUser().getUserId().equals(user.getUserId())){
            throw new ApiRequestException("삭제할 권한이 없습니다.");
        }
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
