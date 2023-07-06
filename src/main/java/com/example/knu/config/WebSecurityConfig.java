package com.example.knu.config;

import com.example.knu.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;



@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userDetailService;

    // 스프링 시큐리티기능 비활성화 부분설정
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
               //.requestMatchers(toH2Console())
                .antMatchers(String.valueOf(toH2Console()))
                .antMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests() //인증과 인가를 설정하겠다....
                .antMatchers("/login","/signup","/user").permitAll() //시큐리티 필요없는 주소들
                .anyRequest().authenticated()

                .and()
                .formLogin() // 폼 로그인 인증 기반
                .loginPage("/login")
                .defaultSuccessUrl("/articles")

                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)

                .and()
                .csrf().disable() //csrf 보안관련
                .build();

    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception{
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    //패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
