package com.ssafy.fit.controller;

import com.ssafy.fit.model.dto.Comment;
import com.ssafy.fit.model.service.CommentService;
import com.ssafy.fit.util.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-comment")
@Api(tags = "댓글 컨트롤러")
public class CommentController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CommentService commentService;

    // 댓글 조회
    @GetMapping("/{articleNo}/comment")
    public ResponseEntity<?> allComments(@PathVariable int articleNo){
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
    public ResponseEntity<String> insertComment(@PathVariable int articleNo, @RequestBody Comment comment, @RequestHeader HttpHeaders header) throws Exception{
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        comment.setArticleNo(articleNo);
        comment.setUserNo(requestUserNo);

        commentService.insertComment(comment);
        return new ResponseEntity<String>("Insert Complete !", HttpStatus.CREATED);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentNo}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentNo, @RequestHeader HttpHeaders header) throws Exception{
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

       Comment requestComment = commentService.selectCommentByNo(commentNo);

        if(requestComment.getUserNo() == requestUserNo) {
            commentService.deleteComment(commentNo);
            return new ResponseEntity<String>("Delete Complete!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("No Permission to Delete",HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글 수정
    @PutMapping("/comment/{commentNo}")
    public ResponseEntity<String> updateComment(@PathVariable int commentNo, @RequestBody Comment comment, @RequestHeader HttpHeaders header) throws Exception{
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        Comment requestComment = commentService.selectCommentByNo(commentNo);

        if(requestComment.getUserNo() == requestUserNo) {
            commentService.updateComment(commentNo, comment.getContent());
            return new ResponseEntity<String>("Update Complete!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("No Permission to Modify",HttpStatus.BAD_REQUEST);
        }
    }

}
