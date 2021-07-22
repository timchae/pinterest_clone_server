package com.clone.pinterest.dto.response;

import com.clone.pinterest.domain.Board;
import com.clone.pinterest.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PinResponseDto {

    private Long pinId;
    private String pinTitle;
    private String pinContent;
    private String pinImage;
    private String pinUrl;
    private Long commentNum;
    private User user;
    private Board board;



    public PinResponseDto(Board board, String pinTitle, String pinContent, User user, Long pinId, String pinImage, String pinUrl, Long commentNum) {
        this.board = board;
        this.pinContent =pinContent;
        this.pinImage = pinImage;
        this.pinTitle = pinTitle;
        this.pinUrl = pinUrl;
        this.pinId = pinId;
        this.commentNum= commentNum;
        this.user = user;
    }
}