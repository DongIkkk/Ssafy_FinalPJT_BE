package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.Video;

public interface VideoService {

	public List<Video> selectVideoAll();
	
	public List<Video> selectVideoAll(String order);
	
	public List<Video> selectVideoPart(String part);

	public Video selectVideoByNo(int no);
	
}
