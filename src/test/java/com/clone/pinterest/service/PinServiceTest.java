package com.clone.pinterest.service;

import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import com.clone.pinterest.dto.request.PinRequestDto;
import com.clone.pinterest.repository.CommentsRepository;
import com.clone.pinterest.repository.PinRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PinServiceTest {

    @InjectMocks
    PinService pinService;

    @Mock
    PinRepository pinRepository;

    @Mock
    CommentsRepository commentsRepository;

    @Test
    @DisplayName("핀 아이디로 핀 내용 찾기")
    void findByPinId(){
        //given
      pinService = new PinService(pinRepository,commentsRepository);
        PinRequestDto pinRequestDto = new PinRequestDto(
                "timtmtm",
                "hahahahah",
                "image",
                "url"
                );

        Long id = 2L;
        Pin pin = new Pin(pinRequestDto);
        when(pinRepository.findById(id)).thenReturn(Optional.of(pin));

        //when
        Pin result = pinService.findPinByID(id);

        //then
        System.out.println(pin.getCommentNum());
        System.out.println(result.getCommentNum());
        assertThat(pin.getPinId()).isEqualTo(result.getPinId());

    }

    @Test
    @DisplayName("핀 내용 수정")
    void editPin(){

        pinService = new PinService(pinRepository,commentsRepository);
        PinRequestDto pinRequestDto = new PinRequestDto(
                "timtmtm",
                "hahahahah",
                "image",
                "url"
        );
        Long id = 2L;
        User user = new User(
                "tim",
                "1q2w3e4r",
                "uImage"
        );
        Pin pin = new Pin(pinRequestDto, user);
        when(pinRepository.findById(id)).thenReturn(Optional.of(pin));

        PinRequestDto pinRequestDto2 = new PinRequestDto(
                "timtmtm",
                "hahahahahooooooo",
                "image",
                "url"
        );
        //when
        Pin result = pinService.editPin(id,pinRequestDto2,user);
        //then
        System.out.println(result.getPinContent());
        assertThat("hahahahahooooooo").isEqualTo(result.getPinContent());



    }


}