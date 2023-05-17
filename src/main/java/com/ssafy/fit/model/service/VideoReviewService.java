package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.VideoReview;

public interface VideoReviewService {

	public List<VideoReview> selectVideoReviewAll(int videoNo);

	public VideoReview selectVideoReviewByNo(int no);

	public void insertVideoReview(VideoReview review);

	public void deleteVideoReview(int no);

	public void updateVideoReview(int reviewNo, String title, String content);

//	public void updateViewCount(int reviewNo);

}
