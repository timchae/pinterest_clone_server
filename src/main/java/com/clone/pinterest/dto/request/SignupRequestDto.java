package com.clone.pinterest.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String userName;
    private String password;
    private String checkPassword;
    private String userImage;
}
