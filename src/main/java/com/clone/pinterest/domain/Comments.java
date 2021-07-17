package com.clone.pinterest.domain;


import com.clone.pinterest.dto.request.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
public class Comments extends Timestamped {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String commentContents;

    @Column(nullable = false)
    private Long pinId;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Boolean liken = Boolean.FALSE;

    public Comments(CommentRequestDto commentRequestDto, Long pinId, User user) {
        this.commentContents = commentRequestDto.getCommentContent();
        this.user = user;
        this.pinId = pinId;
    }

    public void edit(CommentRequestDto commentRequestDto) {
        this.commentContents = commentRequestDto.getCommentContent();
    }
}
