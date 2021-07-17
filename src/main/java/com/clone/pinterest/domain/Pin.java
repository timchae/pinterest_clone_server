package com.clone.pinterest.domain;


import com.clone.pinterest.dto.request.PinRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor
@Entity
@Getter

@Setter
public class Pin extends Timestamped {


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
    @JoinColumn(name = "USER_id", nullable = true)
    private User user;

    public void edit(PinRequestDto pinRequestDto)
    {
        this.pinContent = pinRequestDto.getPinContent();
        this.pinTitle = pinRequestDto.getPinTitle();
        this.pinImage = pinRequestDto.getPinImage();
        this.pinUrl = pinRequestDto.getPinUrl();
    }

    public Pin(PinRequestDto pinRequestDto) {
        this.pinTitle = pinRequestDto.getPinTitle();
        this.pinContent = pinRequestDto.getPinContent();
        this.pinImage = pinRequestDto.getPinImage();
        this.pinUrl = pinRequestDto.getPinUrl();
    }
}
