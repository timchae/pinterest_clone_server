package com.clone.pinterest.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private Long userId;
    private String userName;
    private String Password;
    private String userImage;
}
