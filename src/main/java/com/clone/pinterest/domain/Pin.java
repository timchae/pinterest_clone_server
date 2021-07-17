package com.clone.pinterest.domain;


import com.clone.pinterest.dto.PinRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Pin {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long pinId;

    @Column(nullable = false)
    private String pinTitle;

    @Column(nullable = false)
    private String pinContent;

    @Column(nullable = false)
    private String pinImage;

    @Column(nullable = true)
    private String pinUrl;

    @Column
    private Long commentNum;

    @ManyToOne
    private User user;

    public void edit(PinRequestDto pinRequestDto)
    {
        this.pinContent = pinRequestDto.getPinContent();
        this.pinTitle = pinRequestDto.getPinTitle();
        this.pinImage = pinRequestDto.getPinImage();
        this.pinUrl = pinRequestDto.getPinUrl();
    }

    public Pin(PinRequestDto pinRequestDto){
        this.pinContent = pinRequestDto.getPinContent();
        this.pinTitle = pinRequestDto.getPinTitle();
        this.pinImage = pinRequestDto.getPinImage();
        this.pinUrl = pinRequestDto.getPinUrl();
    }
}
