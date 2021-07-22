package com.clone.pinterest.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {

    private Long boardId;
    private String boardTitle;
    private Long pinNum;
    private List<String> pinImage;

    public BoardResponseDto(Long boardId, String boardTitle) {
        this.boardId= boardId;
        this.boardTitle = boardTitle;


    }
}
