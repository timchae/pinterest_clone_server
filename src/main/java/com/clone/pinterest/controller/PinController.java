package com.clone.pinterest.controller;

import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.security.UserDetailsImpl;
import com.clone.pinterest.service.PinService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import com.clone.pinterest.dto.request.PinRequestDto;
import com.clone.pinterest.dto.response.PinAllResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    // pin 내용 가져오기 api
    @ApiOperation(value = "핀 상세페이지 요청")
    @GetMapping("/pin/{pinid}")
    public Pin eachPin(@PathVariable(name = "pinid") Long id){
        return pinService.findPinByID(id);
    }

    //pin 생성 api
    @ApiOperation(value = "핀 생성하기")
    @PostMapping("/pin")
    public Pin createPin(@RequestBody PinRequestDto pinRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return pinService.createPin(pinRequestDto,userDetails.getUser());
    }

    // pin 내용 수정 api
    @ApiOperation(value = "핀 내용 수정")
    @PutMapping("/pin/{pinid}")
    public Pin pinEdit(@PathVariable(name = "pinid") Long id, @RequestBody PinRequestDto pinRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return pinService.editPin(id,pinRequestDto,userDetails.getUser());
    }

    // pin 삭제 api
    @ApiOperation(value = "핀 삭제")
    @DeleteMapping("/pin/{pinid}")
    public Long pinDelete(@PathVariable(name = "pinid") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return pinService.deletePin(id, userDetails.getUser());
    }

    // 핀 목록 전체 조회
    @ApiOperation(value = "메인페이지 핀 전체 조회")
    @GetMapping("/api/pin")
    public List<PinAllResponseDto> readPin() {
        return pinService.readPin();
    }


    // 핀 목록 전체 조회
    @ApiOperation(value = "메인페이지 핀 전체 조회")
    @GetMapping("/api/pin/page")
    public Page<Pin> readPinPage(@RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        return pinService.readPinPage(page,size);
    }

    // 핀 특정 조회(내가 쓴 핀)
//    @GetMapping("/api/pin/{userName}")
//    public List<MyPinResponseDto> readMyPin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        Long userName = userDetails.getUser().getUserName();
//        return pinService.readMyPin(userName);
//    }
}