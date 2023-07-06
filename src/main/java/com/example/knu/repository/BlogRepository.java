package com.example.knu.repository;

import com.example.knu.domain.blog.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
    // jpa가 crud메소드를 다 만들어줌, 구현체도 필요 없음...
}
