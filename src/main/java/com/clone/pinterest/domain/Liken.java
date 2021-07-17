package com.clone.pinterest.domain;


import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Liken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likenId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long commentId;

    public Liken(Long commentId){
        this.commentId = commentId;
    }
}
