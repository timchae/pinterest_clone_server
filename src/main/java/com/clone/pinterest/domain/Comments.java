package com.clone.pinterest.domain;


import com.clone.pinterest.dto.request.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comments extends Timestamped {


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
    private Boolean liken = Boolean.FALSE;

    public Comments(CommentRequestDto commentRequestDto, Pin pin) {
        this.commentContents = commentRequestDto.getCommentContent();
        this.pin = pin;
    }

    public void edit(CommentRequestDto commentRequestDto) {
        this.commentContents = commentRequestDto.getCommentContent();
    }
}
