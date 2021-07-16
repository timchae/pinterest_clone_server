package com.clone.pinterest.controller;

import com.clone.pinterest.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //로그인
    @PostMapping("/user/login")
    public String userlogin(@RequestBody UserRequestDto userRequestDto){
        return userService.createToken(userRequestDto);
    }

    //회원가입
    @PostMapping("/user/register")
    public void userRegister(@RequestBody SignupRequestDto signupRequestDto){
        userService.registerUser(signupRequestDto);
    }


}
