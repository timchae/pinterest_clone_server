package com.clone.pinterest.controller;

import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.service.PinService;
import lombok.RequiredArgsConstructor;
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

    // pin 내용 가져오기 api
    @GetMapping("/pin/{id}")
    public Pin eachPin(@PathVariable Long id){
        return pinService.findPinByID(id);
    }

    //pin 생성 api
    @PostMapping("/user/pin")
    public Pin createPin(@RequestBody PinRequestDto pinRequestDto){
        return pinService.createPin(pinRequestDto);
    }

    // pin 내용 수정 api
    @PutMapping("/user/pin/{id}")
    public Pin pinEdit(@PathVariable Long id, @RequestBody PinRequestDto pinRequestDto){
        return pinService.editPin(id,pinRequestDto);
    }

    // pin 삭제 api
    @DeleteMapping("/user/pin/{id}")
    public Long pinDelete(@PathVariable Long id){
        return pinService.deletePin(id);
    }

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

