package com.example.knu.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequestDto {
    private String email;
    private String password;
}
