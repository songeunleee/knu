package com.example.knu.web;

import com.example.knu.service.TokenService;
import com.example.knu.web.dto.CreateAcceessTokenResponseDto;
import com.example.knu.web.dto.CreateAccessTokenReuestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/latest/token")
    public ResponseEntity<CreateAcceessTokenResponseDto> createNewAccessToken(@RequestBody CreateAccessTokenReuestDto reuestDto){
        String newAccessToken = tokenService.createNewAccessToken(reuestDto.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAcceessTokenResponseDto(newAccessToken));
    }
}
