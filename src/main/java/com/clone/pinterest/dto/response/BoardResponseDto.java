package com.clone.pinterest.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardResponseDto {

    private Long boardId;
    private String boardTitle;
    private Long pinNum;
    private List<String> pinImage;

}
