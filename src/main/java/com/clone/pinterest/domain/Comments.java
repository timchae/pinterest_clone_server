package com.clone.pinterest.domain;


import com.clone.pinterest.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Comments {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String commentContents;

    @ManyToOne
    private User user;

    @ManyToOne
    private Pin pin;

    @Column(nullable = false)
    private Boolean like = Boolean.FALSE;

    public Comments(CommentRequestDto commentRequestDto, Pin pin) {
        this.commentContents = commentRequestDto.getCommentContent();
        this.pin = pin;
    }

    public void edit(CommentRequestDto commentRequestDto) {
        this.commentContents = commentRequestDto.getCommentContent();
    }
}
