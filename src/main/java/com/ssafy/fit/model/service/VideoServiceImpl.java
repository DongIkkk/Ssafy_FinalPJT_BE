package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.IVideoDao;
import com.ssafy.fit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	private IVideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(IVideoDao videoDao) {
		this.videoDao = videoDao;
	}

	// 비디오 전체 조회
	@Override
	public List<Video> selectVideoAll() {
		return videoDao.selectVideoAll();
	}

	// 비디오 전체 조회 - 정렬
	@Override
	public List<Video> selectVideoAll(String order) {
		return videoDao.selectVideoAll(order);
	}

	// 비디오 조회 - 부위별
	@Override
	public List<Video> selectVideoPart(String part) {
		return videoDao.selectVideoPart(part);
	}


	// 비디오 상세 조회
	@Override
	public Video selectVideoByNo(int no) {
		return videoDao.selectVideoByNo(no);
	}

}
