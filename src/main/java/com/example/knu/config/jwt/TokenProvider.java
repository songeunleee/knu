package com.example.knu.config.jwt;

import com.example.knu.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

// 토큰 생성, 토큰 검증 프로바이더 역할
@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime()+expiredAt.toMillis()),user);
    }

    private String makeToken(Date expiry, User user){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE) // 헤더타입은 jwt타입이다
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now) //현재시각
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id",user.getId())
                .signWith(SignatureAlgorithm.ES256, jwtProperties.getSecret())
                .compact();
    }

    public boolean validToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    // 토큰 기반으로 인증정보를 가져오는 작업
    public Authentication getAthentication(String token){
        Claims claims = getClaims(token);
        // Authentication -> principal, credential, authorities
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                        claims.getSubject(),"",authorities
                ) , //principal
                token,
                authorities
        );
    }

    public Long getUserId(String token){
        Claims claims = getClaims(token);
        return claims.get("id",Long.class);
    } //뭔소리야

    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

}
