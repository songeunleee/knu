package com.example.knu.web.dto;

import lombok.Getter;

@Getter
public class CreateAcceessTokenResponseDto {
    private String accessToken;

    public CreateAcceessTokenResponseDto(String accessToken){
        this.accessToken =accessToken;
    }
}
