package com.example.knu.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 컨트롤러 느낌
@RequiredArgsConstructor
public class TokenAthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider; //서비스 느낌
    private final  static String HEADER_AUTHORIZATION = "Authorization";

    private final  static String TOKEN_PREFIX = "Bearer ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청헤더의 authorization 키 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        // 가져온 값에서 접두사 제거(Bearer)
        String token = getAccessToken(authorizationHeader);

        // 가져온 토큰값이 유효한지 확인하고, 유효하다면 인증정보를 만든다
        if(tokenProvider.validToken(token)){
            Authentication authentication = tokenProvider.getAthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } // 최종 인증 객체, 이거를 시큐리티 컨텍스트 홀더에 넣어줌

        //필터한테 알려줘
        filterChain.doFilter(request,response);

    }

    private String getAccessToken(String athorizationHeader){
        if(athorizationHeader != null && athorizationHeader.startsWith(TOKEN_PREFIX)){
            return athorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
