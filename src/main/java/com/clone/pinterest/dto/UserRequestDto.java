package com.clone.pinterest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String userName;
    private String Password;
    private String userImage;
}
