package com.clone.pinterest.service;

import com.clone.pinterest.domain.Board;
import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import com.clone.pinterest.dto.request.PinRequestDto;
import com.clone.pinterest.dto.response.MyPinResponseDto;
import com.clone.pinterest.dto.response.PinResponseDto;
import com.clone.pinterest.dto.response.PinSearchResponseDto;
import com.clone.pinterest.exception.ApiRequestException;
import com.clone.pinterest.repository.BoardRepository;
import com.clone.pinterest.repository.CommentsRepository;
import com.clone.pinterest.repository.LikenRepository;
import com.clone.pinterest.repository.PinRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    private final LikenRepository likenRepository;


    //pin 내용
    public Pin findPinByID(Long id) {

//        Pin pins = pinRepository.findTop1ByBoard_BoardId(id);
        Pin pin = pinRepository.findById(id).orElseThrow(
                () -> new NullPointerException("NO PiN ID")
        );
        Long commentNum = commentsRepository.countByPinId(id);
        pin.setCommentNum(commentNum);
//        PinResponseDto pinResponseDto = new PinResponseDto(
//                pins.getBoard(),
//                pin.getPinTitle(),
//                pin.getPinContent(),
//                pin.getUser(),
//                pin.getPinId(),
//                pin.getPinImage(),
//                pin.getPinUrl(),
//                commentNum);

        return pin;
    }

    // pin 내용 수정
    @Transactional
    public Pin editPin(Long id, PinRequestDto pinRequestDto, User user) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                () -> new NullPointerException("NO PIN ID")
        );
        if (!pin.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        pin.edit(pinRequestDto);
        return pin;
    }

    // pin 삭제
    @Transactional
    public Long deletePin(Long id, User user) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                () -> new NullPointerException("NO PIN ID")
        );
        if (!pin.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        likenRepository.deleteAllByPinId(id);
        commentsRepository.deleteAllByPinId(id);
        pinRepository.deleteById(id);
        return id;
    }

    // pin 생성
    @Transactional
    public void createPin(PinRequestDto pinRequestDto, User user) {
        Pin pin = new Pin(pinRequestDto, user);
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                ()-> new NullPointerException("No Board")
//        );
//        pin.setBoard(board);
        pinRepository.save(pin);
    }


    // pin 리스트 페이징 조회
    public Page<Pin> readPinPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return pinRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    // pin 특정 조회(내가 쓴 핀)
    public List<MyPinResponseDto> readMyPin(User user) {
        List<Pin> pinList = pinRepository.findAllByUser(user);
        List<MyPinResponseDto> result = pinList.stream()
                .map(pin -> new MyPinResponseDto(pin))
                .collect(Collectors.toList());
        return result;
    }

    // pin 검색 (pinTitle)
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
    @Transactional
    public Pin addpin(Long id, Long boardId, User user) {
        Pin pin = pinRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 핀이 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new ApiRequestException("해당 보드가 존재하지 않습니다.")
        );

        Long userId = user.getUserId();
        Long boardUserId = board.getUser().getUserId();

        if(!userId.equals(boardUserId)){
            throw new ApiRequestException("회원의 보드가 아니어서 추가하지 못했습니다.");
        }

        pin.addBoard(board);
        return pin;
    }
}
