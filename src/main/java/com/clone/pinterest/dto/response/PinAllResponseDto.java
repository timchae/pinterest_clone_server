package com.clone.pinterest.dto.response;

import com.clone.pinterest.domain.Pin;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PinAllResponseDto {

    private Long pinId;
    private String pinTitle;
    private String pinContent;
    private String pinImage;
    private String pinUrl;
    private LocalDateTime createdAt;

    public PinAllResponseDto(Pin pin) {
        this.pinId = pin.getPinId();
        this.pinTitle = pin.getPinTitle();
        this.pinContent = pin.getPinContent();
        this.pinImage = pin.getPinImage();
        this.pinUrl = pin.getPinUrl();
        this.createdAt = pin.getCreatedAt();
    }
}
