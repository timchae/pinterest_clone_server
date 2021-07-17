package com.clone.pinterest.service;


import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import com.clone.pinterest.dto.PinRequestDto;
import com.clone.pinterest.repository.CommentsRepository;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PinService {

    private final PinRepository pinRepository;
    private final CommentsRepository commentsRepository;

    //pin 내용
    public Pin findPinByID(Long id) {

        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO PiN ID")
        );
        Long commentNum = commentsRepository.countByPin_PinId(pin.getPinId());
        pin.setCommentNum(commentNum);
        return pin;
    }

    // pin의 댓글 찾기
    public List<Comments> findCommentById(Long pinId) {
        return commentsRepository.findAllByPin_PinId(pinId);
    }

    // pin 내용 수정
    @Transactional
    public Pin editPin(Long id, PinRequestDto pinRequestDto) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO PIN ID")
        );
        pin.edit(pinRequestDto);
        return pin;
    }

    // pin 삭제
    public Long deletePin(Long id) {
        pinRepository.deleteById(id);
        return id;
    }

    // pin 생성
    public Pin createPin(PinRequestDto pinRequestDto) {
        Pin pin = new Pin(pinRequestDto);
        return pinRepository.save(pin);
    }
}
