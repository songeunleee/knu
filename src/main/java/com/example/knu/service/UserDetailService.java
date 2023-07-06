package com.example.knu.service;

import com.example.knu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //실제로 인증될 사용자를 불러와서 시큐리티 컨텍스트에 관리될 수 있도록 해줘야 한다.
        System.out.println("email = " + email);
        System.out.println("pw = " + userRepository.findByEmail(email).get().getPassword());
        return userRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("Not found : " + email));
    }
}
