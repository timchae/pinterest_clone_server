package com.clone.pinterest.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String userName;
    private String Password;
    private String checkPassword;
    private String userImage;
}
