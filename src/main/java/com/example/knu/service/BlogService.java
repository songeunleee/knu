package com.example.knu.service;

import com.example.knu.domain.blog.Article;
import com.example.knu.repository.BlogRepository;
import com.example.knu.web.dto.AddArticleRequestDto;
import com.example.knu.web.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor // 롬복, 생성자 생략해줌

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    //save
    public Article save(AddArticleRequestDto requestDto){
        return blogRepository.save(requestDto.toEntity());
    }
    // all read

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // read only 1
    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("article not exist : " + id));
    }

    //delete

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    //update

    @Transactional
    public Article update(Long id, UpdateArticleRequestDto requestDto){
        // Step 1.
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("article not exist : " + id));

        //서비스 단에서 조회된 데이터를 업데이트하는 코드가 나오면 된다/
        // Step 2.
        article.update(requestDto.getTitle(),requestDto.getContent());
        // 업데이트될 객체는 만들었는데 이걸 리파지토리로 넘기는 무언가가 없다, 영속성 때문?

        return article;
        // 동작 됨 -> jpa가 해주는 거

    }

}
