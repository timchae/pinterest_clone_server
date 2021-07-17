package com.clone.pinterest.controller;

import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.dto.request.PinRequestDto;
import com.clone.pinterest.dto.response.MyPinResponseDto;
import com.clone.pinterest.dto.response.PinAllResponseDto;
import com.clone.pinterest.dto.response.PinDetailResponseDto;
import com.clone.pinterest.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    // 핀 작성
    @PostMapping("/api/pin")
    public Pin creatPin(@RequestBody PinRequestDto pinRequestDto ) {
        return pinService.creatPin(pinRequestDto);
    }

    // 핀 목록 전체 조회
    @GetMapping("/api/pin")
    public List<PinAllResponseDto> readPin() {
        return pinService.readPin();
    }

    // 핀 상세 조회
    @GetMapping("/api/pin/{pinId}")
    public PinDetailResponseDto readDetail(@PathVariable Long pinId) {
        return pinService.readDetail(pinId);
    }

    // 핀 특정 조회(내가 쓴 핀)
//    @GetMapping("/api/pin/{userName}")
//    public List<MyPinResponseDto> readMyPin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        Long userName = userDetails.getUser().getUserName();
//        return pinService.readMyPin(userName);
//    }
}