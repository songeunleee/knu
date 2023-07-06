package com.example.knu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 전체 전원 on 시켜주는...
@SpringBootApplication
public class KnuApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnuApplication.class, args);
    }

}
