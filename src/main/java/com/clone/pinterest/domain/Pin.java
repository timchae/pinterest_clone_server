package com.clone.pinterest.domain;


import com.clone.pinterest.dto.request.PinRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class Pin extends Timestamped {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long pinId;

    @Column(nullable = false)
    private String pinTitle;

    @Column(nullable = false)
    private String pinContent;

    @Column(nullable = false)
    private String pinImage;

    @Column(nullable = true)
    private String pinUrl;

    @Column
    private Long commentNum;

    @ManyToOne
    @JoinColumn(name = "USER_id", nullable = false)
    private User user;


    @ManyToMany
    @Column(nullable = false)
    private List<Board> board;

    public void edit(PinRequestDto pinRequestDto)
    {
        this.pinContent = pinRequestDto.getPinContent();
        this.pinTitle = pinRequestDto.getPinTitle();
        this.pinImage = pinRequestDto.getPinImage();
        this.pinUrl = pinRequestDto.getPinUrl();
    }

    public Pin(PinRequestDto pinRequestDto, User user) {
        this.pinTitle = pinRequestDto.getPinTitle();
        this.pinContent = pinRequestDto.getPinContent();
        this.pinImage = pinRequestDto.getPinImage();
        this.pinUrl = pinRequestDto.getPinUrl();
        this.user = user;
    }

    public void setBoard(Board board) {
        this.board.add(board);
    }

    public void addBoard(Board board){
        this.board.add(board);
    }
}

