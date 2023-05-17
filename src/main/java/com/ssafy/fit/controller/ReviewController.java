package com.ssafy.fit.controller;

import com.ssafy.fit.model.dto.VideoReview;
import com.ssafy.fit.model.service.VideoReviewService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-review")
@Api(tags = "리뷰 컨트롤러")
public class ReviewController {

    @Autowired
    private VideoReviewService reviewService;

    // 리뷰조회
    @GetMapping("/{videoNo}/review")
    public ResponseEntity<?> allReviews(@RequestParam int videoNo){
        List<VideoReview> reviewList = reviewService.selectVideoReviewAll(videoNo);
        return new ResponseEntity<List<VideoReview>>(reviewList, HttpStatus.OK);
    }

    // 리뷰 상세 조회
    @GetMapping("/review/{no}")
    public ResponseEntity<?> selectReview(@RequestParam int no){
        VideoReview videoReview = reviewService.selectVideoReviewByNo(no);
        return new ResponseEntity<VideoReview>(videoReview, HttpStatus.OK);
    }

    // 리뷰 작성
    @PostMapping("/{videoNo}/review")
    public ResponseEntity<String> insertReview(@RequestParam int videoNo, VideoReview review){
        review.setVideoNo(videoNo);
        reviewService.insertVideoReview(review);
        return new ResponseEntity<String>("Insert Complete !", HttpStatus.CREATED) ;
    }

    // 리뷰 삭제
    @DeleteMapping("/review/{no}")
    public ResponseEntity<String> deleteReview(@RequestParam int no){
        reviewService.deleteVideoReview(no);
        return new ResponseEntity<String>("Delete Complete!", HttpStatus.OK);
    }

    // 리뷰 수정
    @PutMapping("/review/{no}")
    public ResponseEntity<String> updateReview(@RequestParam int no, VideoReview review){
        reviewService.updateVideoReview(no, review.getTitle(), review.getContent());
        return new ResponseEntity<String>("Update Complete!",HttpStatus.OK);
    }

}
