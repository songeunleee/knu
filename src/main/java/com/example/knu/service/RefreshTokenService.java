package com.example.knu.service;


import com.example.knu.domain.token.RefreshToken;
import com.example.knu.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                ()-> new IllegalArgumentException("unexpected token")
        );
    }



}
