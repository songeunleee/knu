package com.example.knu.web.dto;

import com.example.knu.domain.blog.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {
    // 얘는 엔티티를 디티오로
    private String title;
    private String content;

    public ArticleResponseDto(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
    }

}
