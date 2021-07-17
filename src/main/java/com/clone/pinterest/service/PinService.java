package com.clone.pinterest.service;

import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.dto.request.PinRequestDto;
import com.clone.pinterest.dto.response.PinAllResponseDto;
import com.clone.pinterest.dto.response.PinDetailResponseDto;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PinService {

    private final PinRepository pinRepository;

    @Transactional
    public Pin creatPin(PinRequestDto pinRequestDto ) {
        Pin pin = new Pin(pinRequestDto);
        pinRepository.save(pin);
        return pin;
    }

    @Transactional
    public List<PinAllResponseDto> readPin() {
        List<Pin> pinList = pinRepository.findAllByOrderByCreatedAtDesc();
        List<PinAllResponseDto> result = pinList.stream()
                .map(pin -> new PinAllResponseDto(pin))
                .collect(Collectors.toList());
        return result;
    }

    @Transactional
    public PinDetailResponseDto readDetail(Long pinId) {
        Pin pin = pinRepository.findById(pinId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 없습니다."));
        pinRepository.findById(pinId);
        PinDetailResponseDto pinDetailResponseDto = new PinDetailResponseDto(pin);
        return pinDetailResponseDto;
    }

//    @Transactional
//    public MyPinResponseDto readMyPin(String userName) {
//        List<Pin> pinList = userRepository.findAllByUserName(userName);
//        List<MyPinResponseDto> result = pinList.stream()
//                .map(pin -> new MyPinResponseDto(pin))
//                .collect(Collectors.toList());
//        return result;
//    }
}