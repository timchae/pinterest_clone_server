package com.clone.pinterest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PinRequestDto {
    private String pinTitle;
    private String pinContent;
    private String pinImage;
    private String pinUrl;
}
