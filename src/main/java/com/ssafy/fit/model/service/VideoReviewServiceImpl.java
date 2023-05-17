package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dao.IVideoReviewDao;
import com.ssafy.fit.model.dto.VideoReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoReviewServiceImpl implements VideoReviewService{

    private IVideoReviewDao reviewDao;

    @Autowired
    public VideoReviewServiceImpl(IVideoReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    // 리뷰 전체 조회
    @Override
    public List<VideoReview> selectVideoReviewAll(int videoNo) {
        return reviewDao.selectVideoReviewAll(videoNo);
    }

    // 리뷰 상세 조회
    @Override
    public VideoReview selectVideoReviewByNo(int no) {
        reviewDao.updateViewCount(no);
        return reviewDao.selectVideoReviewByNo(no);
    }

    // 리뷰 작성
    @Override
    public void insertVideoReview(VideoReview review) {
        reviewDao.insertVideoReview(review);
    }

    // 리뷰 삭제
    @Override
    public void deleteVideoReview(int no) {
        reviewDao.deleteVideoReview(no);
    }

    // 리뷰 수정
    @Override
    public void updateVideoReview(int reviewNo, String title, String content) {
        reviewDao.updateVideoReview(reviewNo, title,content);
    }
}
