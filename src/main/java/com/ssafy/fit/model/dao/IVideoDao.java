package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.dto.Video;

public interface IVideoDao {
	
	public List<Video> selectVideoAll();
	
	public List<Video> selectVideoAll(String order);
	
	public List<Video> selectVideoPart(String part);
	
	public Video selectVideoByNo(int no);
	
}
