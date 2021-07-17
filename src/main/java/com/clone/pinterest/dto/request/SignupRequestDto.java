package com.clone.pinterest.dto.request;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String userName;
    private String password;
    private String checkPassword;
    private String userImage;
}
