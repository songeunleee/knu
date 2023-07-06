package com.example.knu.service;

import com.example.knu.config.jwt.TokenProvider;
import com.example.knu.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
// 토큰만 관리하는 서비스
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        // 토큰 유효성 검증에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

    // 리프레시와 엑세스 토큰 둘다 발급하는 (최초 로그인)

    // 리프레시 토큰이 만료되었을 때... 리프레시토큰을 또 주는 경우 update
    // 프로바이더를 만들어서 분리시킨다

}
