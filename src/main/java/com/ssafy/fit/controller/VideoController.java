package com.ssafy.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.service.VideoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api-video")
@Api(tags = "비디오 컨트롤러")
public class VideoController {
	
	@Autowired
	private VideoService videoService;

	// 전체 비디오 조회 - 정렬, 부위별 조회 가능
	@GetMapping("/videos")
	public ResponseEntity<List<Video>> allVideo(@RequestParam(value = "order", required = false) String order,
												@RequestParam(value = "part", required = false) String part){
		// String ordertemp = order; //정렬 동적쿼리
		String parttemp = part;
		if(parttemp==null || parttemp.isEmpty()){
			List<Video> vlist = videoService.selectVideoAll();
			return new ResponseEntity<List<Video>>(vlist, HttpStatus.OK);
		}
		List<Video> vlist = videoService.selectVideoPart(parttemp);
		return new ResponseEntity<List<Video>>(vlist, HttpStatus.OK);
	}

	// 비디오 상세 조회
	@GetMapping("/video/{no}")
	public Video selectVideo(@RequestParam int no){
		return videoService.selectVideoByNo(no);
	}
	
}
