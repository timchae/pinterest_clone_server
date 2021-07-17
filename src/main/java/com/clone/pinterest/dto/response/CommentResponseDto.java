package com.clone.pinterest.dto.response;


import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private Long commentId;
    private String commentContents;
    private Long likeNum;
    private User user;
    private Pin pin;
    private Boolean liken = Boolean.FALSE;

    public CommentResponseDto(Comments comment,Long count,boolean liken) {
        this.commentContents = comment.getCommentContents();
        this.commentId = comment.getCommentId();
        this.pin = comment.getPin();
        this.user = comment.getUser();
        this.likeNum = count;
        this.liken = liken;

    }
}
