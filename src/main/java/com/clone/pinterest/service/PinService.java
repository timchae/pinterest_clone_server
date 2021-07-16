package com.clone.pinterest.service;


import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
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

    private PinRepository pinRepository;
    private CommentsRepository commentsRepository;

    //pin 내용
    public Pin findPinByID(Long id) {
        return pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO PiN ID")
        );
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
}
