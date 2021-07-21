package com.clone.pinterest.dto.response;

import com.clone.pinterest.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PinSearchResponseDto {

    private  Long pinId;
    private  User user;
    private  String pinTitle;
    private  String pinContent;
    private  String pinImage;
    private  String pinUrl;
    private  LocalDateTime createdAt;

    public PinSearchResponseDto(Long pinId, User user, String pinTitle, String pinContent, String pinImage, String pinUrl, LocalDateTime createdAt) {
        this.pinId = pinId;
        this.user = user;
        this.pinTitle = pinTitle;
        this.pinContent = pinContent;
        this.pinImage = pinImage;
        this.pinUrl = pinUrl;
        this.createdAt = createdAt;
    }
}
