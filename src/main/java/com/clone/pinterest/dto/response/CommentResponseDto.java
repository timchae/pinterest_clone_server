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
    private Long pinId;
    private Boolean liken = Boolean.FALSE;

    public CommentResponseDto(Comments comment,Long pinId,Long count,boolean liken) {
        this.commentContents = comment.getCommentContents();
        this.commentId = comment.getCommentId();
        this.pinId = pinId;
        this.user = comment.getUser();
        this.likeNum = count;
        this.liken = liken;

    }
}
// 상세핀에 어느유저 어느 보드에 저장했는지
// 보드 생성 수정