package com.clone.pinterest.dto;


import com.clone.pinterest.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PinRequestDto {

    private String pinTitle;
    private String pinContent;
    private String pinImage;
    private String pinUrl;
    private User user;
}
