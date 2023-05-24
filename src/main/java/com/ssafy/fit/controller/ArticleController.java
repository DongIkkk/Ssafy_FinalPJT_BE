package com.ssafy.fit.controller;

import com.ssafy.fit.model.dto.Article;
import com.ssafy.fit.model.service.ArticleService;
import com.ssafy.fit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api-article")
@Api(tags = "게시글 컨트롤러")
public class ArticleController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ArticleService articleService;

    // 경로 이름길어서 fileDir로 쓰는듯
    @Value("/Users/jeongdong-gyo/Desktop/FinalWorkspace/Ssafy_FinalPJT_FE/src/assets/")
    private String fileDir;

    //전체 게시글 조회
    @GetMapping("/articles")
    public ResponseEntity<?> allArticles(){
        List<Article> atclist = articleService.selectArticleAll();
        return new ResponseEntity<List<Article>>(atclist, HttpStatus.OK);
    }

    //게시글 상세 조회
    @GetMapping("/article/{articleNo}")
    public ResponseEntity<?> selectArticle(@PathVariable int articleNo){
        articleService.increaseViewCnt(articleNo);
        Article thisAtc = articleService.selectArticleByNo(articleNo);
        return new ResponseEntity<Article>(thisAtc, HttpStatus.OK);
    }


    //내가 쓴 게시글 조회
    @GetMapping("/articles/{userNo}")
    public ResponseEntity<?> selectMyArticles(@PathVariable int userNo){
        List<Article> myAtcs = articleService.selectMyArticle(userNo);
        return new ResponseEntity<List<Article>>(myAtcs, HttpStatus.OK);
    }

    //게시글 생성
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestHeader HttpHeaders header, Article article, @RequestParam("file") MultipartFile file) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        String fullPath= "";
        String ranUUID = UUID.randomUUID().toString();
        String OriginFileName = "";
        if (file!= null) {
            OriginFileName = file.getOriginalFilename();
            fullPath = fileDir + ranUUID + OriginFileName; // 저장할 경로 만드는 코드
            file.transferTo(new File(fullPath)); // 진짜저장하는코드
            System.out.println(fullPath);
        }
        article.setImgFullpath(fullPath); //이미지에 경로를 저장
        article.setImgName(ranUUID+OriginFileName);
        article.setUserNo(requestUserNo);
        articleService.insertArticle(article);
        return new ResponseEntity<String>("Insert Complete!", HttpStatus.CREATED);
    }

    //게시글 수정
    @PutMapping("/article/{articleNo}")
    public ResponseEntity<?> updateArticle(@RequestHeader HttpHeaders header, @PathVariable int articleNo,Article article, @RequestParam("file") MultipartFile file) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        Article thisArticle = articleService.selectArticleByNo(articleNo);

        if(thisArticle.getUserNo() != requestUserNo){
            return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.OK);
        }

        if (file!= null) {
            String fullPath= "";
            String ranUUID = UUID.randomUUID().toString();
            String OriginFileName = "";

            System.out.println("here");
            OriginFileName = file.getOriginalFilename();
            fullPath = fileDir + ranUUID + OriginFileName;
            file.transferTo(new File(fullPath));

            thisArticle.setImgName(ranUUID+OriginFileName);
            thisArticle.setImgFullpath(fullPath);
        }

        thisArticle.setContent(article.getContent());

        articleService.updateArticle(articleNo, thisArticle);
        return new ResponseEntity<String>("Update Complete!", HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/article/{articleNo}")
    public ResponseEntity<?> deleteArticle(@RequestHeader HttpHeaders header, @PathVariable int articleNo) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        Article thisArticle = articleService.selectArticleByNo(articleNo);

        if(thisArticle.getUserNo() != requestUserNo){
            return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }

        articleService.deleteArticle(articleNo);
        return new ResponseEntity<String>("delete Complete!", HttpStatus.OK);
    }

    // 게시글 좋아요
    @PostMapping("/like/{articleNo}")
    public ResponseEntity<?> articleLike(@RequestHeader HttpHeaders header, @PathVariable int articleNo) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        articleService.articleLike(articleNo, requestUserNo);
        return new ResponseEntity<>("like", HttpStatus.CREATED);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("like/{articleNo}")
    public ResponseEntity<?> articleUnlike(@RequestHeader HttpHeaders header, @PathVariable int articleNo) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        articleService.articleUnlike(articleNo, requestUserNo);
        return new ResponseEntity<>("unlike", HttpStatus.OK);
    }


}

