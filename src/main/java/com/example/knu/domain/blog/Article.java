package com.example.knu.domain.blog;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter// 롬복 애너테이션
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 롬복
@EntityListeners(AuditingEntityListener.class)

@Entity // 엔티티의 모습을 갖춰야 함
        // 테이블과 같음
        // 프라이머리 키 같은걸 지정 해 줘야함
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title,String content){
        this.title = title;
        this.content = content;
    }

    // 업데이트 하는 기능을 명시한다.
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
