package com.example.knu.repository;


import com.example.knu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //이메일 방식으로 회원 조회
    Optional<User> findByEmail(String email);

}
