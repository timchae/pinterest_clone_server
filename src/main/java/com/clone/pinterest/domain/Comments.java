package com.clone.pinterest.domain;


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
}
