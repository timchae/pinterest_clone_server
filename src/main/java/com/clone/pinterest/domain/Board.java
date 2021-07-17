package com.clone.pinterest.domain;

import com.clone.pinterest.dto.request.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Board extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long boardId;

    @Column(nullable = false)
    private String boardTitle;

    @OneToMany
    private List<Pin> pin;

    @ManyToOne
    private User user;

    public Board(BoardRequestDto boardRequestDto){
        this.boardTitle = boardRequestDto.getBoardTitle();
    }
}
