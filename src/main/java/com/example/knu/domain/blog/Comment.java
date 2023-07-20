package com.example.knu.domain.blog;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "content",nullable = false)
    private String content;
    @Column(name = "board_id",nullable = false,updatable = false)
    private Long boardId;
    @Column(name = "user_id",nullable = false,updatable = false)
    private Long userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Comment(String content, Long boardId, Long userId){
        this.content = content;
        this.boardId = boardId;
        this.userId = userId;
    }
}
