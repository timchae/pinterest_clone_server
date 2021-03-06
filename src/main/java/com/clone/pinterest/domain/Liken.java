package com.clone.pinterest.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Liken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likenId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private Long pinId;

    public Liken(Long pinId,Long commentId, Long userId){
        this.commentId = commentId;
        this.userId = userId;
        this.pinId = pinId;
    }
}
