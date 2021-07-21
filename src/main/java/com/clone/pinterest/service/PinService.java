package com.clone.pinterest.service;

import com.clone.pinterest.domain.Comments;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import com.clone.pinterest.dto.request.PinRequestDto;
import com.clone.pinterest.dto.response.MyPinResponseDto;
import com.clone.pinterest.dto.response.PinSearchResponseDto;
import com.clone.pinterest.repository.BoardRepository;
import com.clone.pinterest.repository.CommentsRepository;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.dto.response.PinAllResponseDto;
import com.clone.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PinService {

    private final PinRepository pinRepository;
    private final CommentsRepository commentsRepository;
    private final BoardRepository boardRepository;


    //pin 내용
    public Pin findPinByID(Long id) {

        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO PiN ID")
        );
        Long commentNum = commentsRepository.countByPinId(id);
        pin.setCommentNum(commentNum);
        return pin;
    }

    // pin 내용 수정
    @Transactional
    public Pin editPin(Long id, PinRequestDto pinRequestDto, User user) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO PIN ID")
        );
        if(!pin.getUser().getUserId().equals(user.getUserId())){
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        pin.edit(pinRequestDto);
        return pin;
    }

    // pin 삭제
    public Long deletePin(Long id, User user) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("NO PIN ID")
        );
        if(!pin.getUser().getUserId().equals(user.getUserId())){
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        pinRepository.deleteById(id);
        return id;
    }

    // pin 생성
    public Pin createPin(PinRequestDto pinRequestDto, User user) {
        Pin pin = new Pin(pinRequestDto, user);
        return pinRepository.save(pin);
    }

    // pin 리스트 조회
    @Transactional
    public List<PinAllResponseDto> readPin() {
        List<Pin> pinList = pinRepository.findAllByOrderByCreatedAtDesc();
        List<PinAllResponseDto> result = pinList.stream()
                .map(pin -> new PinAllResponseDto(pin))
                .collect(Collectors.toList());
        return result;
    }


    // pin 리스트 페이징 조회
    @Transactional
    public Page<Pin> readPinPage(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return pinRepository.findAll(pageable);
    }

    // pin 특정 조회(내가 쓴 핀)
    @Transactional
    public List<MyPinResponseDto> readMyPin(User user) {
        List<Pin> pinList = pinRepository.findAllByUser(user);
        List<MyPinResponseDto> result = pinList.stream()
                .map(pin -> new MyPinResponseDto(pin))
                .collect(Collectors.toList());
        return result;
    }
//    @Transactional
//    public PinDetailResponseDto readDetail(Long pinId) {
//        Pin pin = pinRepository.findById(pinId).orElseThrow(
//                () -> new IllegalArgumentException("해당 아이디가 없습니다."));
//        pinRepository.findById(pinId);
//        PinDetailResponseDto pinDetailResponseDto = new PinDetailResponseDto(pin);
//        return pinDetailResponseDto;
//    }

    // pin 검색 (pinTitle)
    @Transactional
    public List<PinSearchResponseDto> readSearchPin(String keyword) {
        List<Pin> pins = pinRepository.findByPinTitleContaining(keyword);
        List<PinSearchResponseDto> pinSearchResponseDtos = new ArrayList<>();

        for (Pin pin : pins ) {
            PinSearchResponseDto pinSearchResponseDto = new PinSearchResponseDto (
                    pin.getPinId(),
                    pin.getUser(),
                    pin.getPinTitle(),
                    pin.getPinContent(),
                    pin.getPinImage(),
                    pin.getPinUrl(),
                    pin.getCreatedAt()
            );

            pinSearchResponseDtos.add(pinSearchResponseDto);
        }

        return pinSearchResponseDtos;
    }
}
