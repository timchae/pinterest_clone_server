package com.clone.pinterest.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Pin {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long pinId;

    @Column(nullable = false)
    private String pinTitle;

    @Column(nullable = false)
    private String pinContent;

    @Column(nullable = false)
    private String pinImage;

    @Column(nullable = false)
    private String pinUrl;

    @ManyToOne
    private User user;
}
