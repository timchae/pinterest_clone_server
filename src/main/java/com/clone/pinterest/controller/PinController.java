package com.clone.pinterest.controller;


import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.dto.PinRequestDto;
import com.clone.pinterest.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PinController {


    private PinService pinService;

    // pin 내용 가져오기 api
    @GetMapping("/pin/{id}")
    public Pin eachPin(@PathVariable Long id){
        return pinService.findPinByID(id);
    }

    // pin의 댓글 가져오기 api
    @GetMapping("/pin/comment/{id}")
    public List<Comments> pinComment(@PathVariable Long id){
        return pinService.findCommentById(id);
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

}
