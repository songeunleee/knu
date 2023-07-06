package com.example.knu.web;

import com.example.knu.domain.blog.Article;
import com.example.knu.service.BlogService;
import com.example.knu.web.dto.ArticleListViewResponse;
import com.example.knu.web.dto.ArticleViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
//@RestController는 리퀘스트바디랑 컨트롤러 바디가 합쳐집
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("articles",articles);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article",new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article") // 신규 글 작성, 특정 글 수정을 동시에 처리
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){
            // 신규 등록
            model.addAttribute("article",new ArticleViewResponse());
        }else{
            // 수정 작업
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }
        return "newArticle";
    }


}
