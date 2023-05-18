package com.ssafy.fit.controller;

import com.ssafy.fit.model.dto.Comment;
import com.ssafy.fit.model.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-comment")
@Api(tags = "댓글 컨트롤러")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 조회
    @GetMapping("/{articleNo}/comment")
    public ResponseEntity<?> allComments(@RequestParam int articleNo){
        List<Comment> commentList = commentService.selectCommentAll(articleNo);
        return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);
    }

    //========리뷰상세조회 기능은 없음
    // 리뷰 상세 조회
//    @GetMapping("/review/{no}")
//    public ResponseEntity<?> selectReview(@RequestParam int no){
//        VideoReview videoReview = reviewService.selectVideoReviewByNo(no);
//        return new ResponseEntity<VideoReview>(videoReview, HttpStatus.OK);
//    }


    // 댓글 작성
    @PostMapping("/{articleNo}/comment")
    public ResponseEntity<String> insertComment(@RequestParam int articleNo, Comment comment){
        comment.setArticleNo(articleNo);
        commentService.insertComment(comment);
        return new ResponseEntity<String>("Insert Complete !", HttpStatus.CREATED);
    }

    // 리뷰 삭제
    @DeleteMapping("/comment/{commentNo}")
    public ResponseEntity<String> deleteComment(@RequestParam int commentNo){
        commentService.deleteComment(commentNo);
        return new ResponseEntity<String>("Delete Complete!", HttpStatus.OK);
    }

    // 리뷰 수정
    @PutMapping("/comment/{commentNo}")
    public ResponseEntity<String> updateComment(@RequestParam int commentNo, Comment comment){
        commentService.updateComment(commentNo, comment.getContent());
        return new ResponseEntity<String>("Update Complete!",HttpStatus.OK);
    }

}
