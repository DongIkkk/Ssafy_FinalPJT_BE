package com.ssafy.fit.controller;

import com.ssafy.fit.model.dto.Article;
import com.ssafy.fit.model.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

@RestController
@RequestMapping("/api-article")
@Api(tags = "게시글 컨트롤러")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    //전체 게시글 조회
    @GetMapping("/articles")
    public ResponseEntity<?> allArticles(){
        List<Article> atclist = articleService.selectArticleAll();
        return new ResponseEntity<List<Article>>(atclist, HttpStatus.OK);
    }

    //게시글 상세 조회
    @GetMapping("/article/{articleNo}")
    public ResponseEntity<?> selectArticle(@PathVariable int articleNo){
        Article thisAtc = articleService.selectArticleByNo(articleNo);
        return new ResponseEntity<Article>(thisAtc, HttpStatus.OK);
    }


    //내가 쓴 게시글 조회
    @GetMapping("/articles/{userNo}")
    public ResponseEntity<?> selectMyArticles(@PathVariable int userNo){
    	System.out.println("여기");
        List<Article> myAtcs = articleService.selectMyArticle(userNo);
        System.out.println("여기2");
        return new ResponseEntity<List<Article>>(myAtcs, HttpStatus.OK);
    }

    //게시글 생성
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody Article article){
        articleService.insertArticle(article);
        return new ResponseEntity<String>("Insert Complete!", HttpStatus.CREATED);
    }

    //게시글 수정
    @PutMapping("/article/{articleNo}")
    public ResponseEntity<?> updateArticle(@PathVariable int articleNo, @RequestBody Article article){
        articleService.updateArticle(articleNo, article);
        return new ResponseEntity<String>("Update Complete!", HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/article/{articleNo}")
    public ResponseEntity<?> deleteArticle(@PathVariable int articleNo){
        articleService.deleteArticle(articleNo);
        return new ResponseEntity<String>("delete Complete!", HttpStatus.OK);
    }

    //게시글 좋아요 취소 추가,,

}

