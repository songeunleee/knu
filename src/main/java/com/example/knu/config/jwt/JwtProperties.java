package com.example.knu.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component //빈 등록
@ConfigurationProperties("jwt") // application.yml 을 뒤져라
public class JwtProperties {
    private String issuer;
    private String secret;
}
