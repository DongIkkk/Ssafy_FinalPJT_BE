package com.ssafy.fit.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.fit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
public class UserController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/tokenLogin")
	public ResponseEntity<Map<String, Object>> tokenLogin(User user) {
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		try {
			if (user.getUserId() != null || user.getUserId().length() > 0) {
				result.put("access-token", jwtUtil.createToken("id", user.getUserId()));
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			} else{
				result.put("message", "FAIL");
				status = HttpStatus.NO_CONTENT;
			}
		} catch (UnsupportedEncodingException e) {
			result.put("message", "FAIL");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	// 사용자 리스트 조회
	@GetMapping("/users")
	public ResponseEntity<?> userList(){
		List<User> ulist = userService.selectUserAll();

		if(ulist==null || ulist.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<User>>(ulist, HttpStatus.OK);
	}

	/*
	로그인 기능 토큰방식으로 대체 - 사라질예정
	 */
	// 로그인 기능
//	@PostMapping("/login")
//	public ResponseEntity<?> login(User user, HttpSession session){
//		if(userService.isPassword(user.getUserId(), user.getPassword())){
//			session.setAttribute("loginUser", user.getUserId());
//			return new ResponseEntity<String>(user.getUserId()+"success in Login", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("fail to Login", HttpStatus.UNAUTHORIZED);
//	}

	// 로그아웃 기능
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session){
		session.invalidate();
		return new ResponseEntity<String>("Logout Complete", HttpStatus.OK);
	}

	// 회원가입 페이지로 이동
	@GetMapping("/signup")
	public String SignupForm(){
		return "Go to SignUp Page";
	}

	// 회원가입시 아이디 중복 체크
	@PostMapping("/signup")
	public ResponseEntity<?> signup(User user){
		if(userService.isDuplicate(user.getUserId())) {
			int result = userService.insertUser(user);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("ID Duplicate", HttpStatus.BAD_REQUEST);
	}

	// 찜리스트 추가
//	@PostMapping("/likevideo")
//	public ResponseEntity<?> like(User user, Video video){
//		String userId = user.getUserId();
//		int vNo = video.getNo();
//
//		userService.likeVideo(userId, vNo);
//		return new ResponseEntity<String>(vNo+" video like Complete!!", HttpStatus.CREATED);
//	}
//
//	// 찜리스트 삭제
//	@DeleteMapping("/likevideo")
//	public ResponseEntity<?> unlike(User user, Video video){
//		String userId = user.getUserId();
//		int vNo = video.getNo();
//
//		userService.unLikeVideo(userId, vNo);
//		return new ResponseEntity<String>(vNo+" video Unlike Complete!!", HttpStatus.CREATED);
//	}
//
//	// 찜리스트 조회
//	@GetMapping("/likevideo")
//	public ResponseEntity<?> mylikelist(User user){
//		String userId = user.getUserId();
//		List<Integer> likelist = userService.selectLikeVideoNumbers(userId);
//
//		if(likelist==null || likelist.size()==0)
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		return new ResponseEntity<List<Integer>>(likelist, HttpStatus.OK);
//	}

	// 팔로우
	@PostMapping("/follow")
	public ResponseEntity<?> follow(int from, int to){
		userService.follow(from, to);
		return new ResponseEntity<String>("Following Complete",HttpStatus.CREATED);
	}

	// 언팔로우
	@DeleteMapping("/follow")
	public ResponseEntity<?> unfollow(int from, int to){
		userService.unFollow(from, to);
		return new ResponseEntity<String>("Unfollow Complete", HttpStatus.OK);
	}

	// 팔로우 리스트 조회
	@GetMapping("/follow")
	public ResponseEntity<?> followlist(User user){
		int userNo = user.getUserNo();

		List<Integer>[] flist = new List[2];

		List<Integer> myFollower = userService.myFollower(userNo);
		List<Integer> myFollowing = userService.myFollowing(userNo);

		flist[0] = myFollower;
		flist[1] = myFollowing;

		return new ResponseEntity<List<Integer>[]>(flist, HttpStatus.OK);
	}

}
