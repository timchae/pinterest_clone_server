package com.clone.pinterest.service;

import com.clone.pinterest.domain.User;
import com.clone.pinterest.dto.SignupRequestDto;
import com.clone.pinterest.dto.UserRequestDto;
import com.clone.pinterest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUserName();

        Optional<User> found = userRepository.findByUserName(username);
        if (found.isPresent()) {
            throw new ApiRequestException("중복된 사용자 ID 가 존재합니다.");
        }

        String password = signupRequestDto.getPassword();
        String passwordCheck = signupRequestDto.getCheckPassword();

        if (!password.isEmpty() && !passwordCheck.isEmpty()) {
            if (password.length() >= 6 && password.length() <= 20) {
                if (!password.equals(passwordCheck)) {
                    throw new ApiRequestException("패스워드가 일치하지 않습니다!");
                }
            } else {
                throw new ApiRequestException("비밀번호는  6~20자리를 사용해야 합니다.");
            }
        } else {
            throw new ApiRequestException("패스워드를 입력해 주세요.");
        }

        String userImage = signupRequestDto.getUserImage();

        password = passwordEncoder.encode(password);

        User user = new User(username, password, userImage);
        userRepository.save(user);
    }

    public String makeJwtToken(UserRequestDto userRequestDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userRequestDto.getUserName(),userRequestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.createToken(authentication);

    }


}
