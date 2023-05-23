package com.ssafy.fit.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ssafy.fit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
public class UserController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@Value("/Users/dongik/Desktop/Coding/ssafy_project/macdongnald/ssafit_spring_dong_FE/macdongnaldsfront/src/assets/profile_img/")
	private String profileImgDir;

	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(User user, @RequestParam(value = "profileImg", required = false)
																MultipartFile profileImg) throws Exception {
		if (profileImg!= null) {
			String fullPath= "";
			String ranUUID = UUID.randomUUID().toString();
			String OriginProfileImgName = "";
			OriginProfileImgName = profileImg.getOriginalFilename();
			fullPath = profileImgDir + ranUUID + OriginProfileImgName; // 저장할 경로 만드는 코드
			profileImg.transferTo(new File(fullPath)); // 진짜저장하는코드
			user.setProfileImgFullpath(fullPath);
			user.setProfileImgName(ranUUID+OriginProfileImgName);
		}else{
			user.setProfileImgFullpath("/Users/dongik/Desktop/Coding/ssafy_project/macdongnald/ssafit_spring_dong_FE/macdongnaldsfront/src/assets/profile_img/profile_default_test.png");
			user.setProfileImgName("profile_default_test.png");
		}

		int result = userService.insertUser(user);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	// 회원가입시 아이디 중복 체크
	@PostMapping("/id-duplicate")
	public ResponseEntity<?> idDuplicateCheck(@RequestParam("tryId") String tryId){
		if(userService.isDuplicate(tryId)) {
			return new ResponseEntity<String>("You Can Make It", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID Duplicate", HttpStatus.OK);
	}

	//로그인(jwt Token 발급)
	@PostMapping("/tokenLogin")
	public ResponseEntity<Map<String, Object>> tokenLogin(@RequestBody User user) {
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		// 입력한 id에 해당하는 유저 가져옴
		User targetUser = userService.selectUserByUserId(user.getUserId());

		// 가져온게 없다면 존재하지 않는 id
		if(targetUser == null){
			result.put("message", "Your ID is not exist");
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(result, status);
		}

		// 있다면 진행 -> 비밀번호 일치하는지 체크
		boolean correctPW = false;
		if (targetUser.getPassword().equals(user.getPassword())) correctPW = true;

		// 비밀번호 일치한다면
		if (correctPW) {
			try {
				// 토큰생성
				result.put("access-token", jwtUtil.createToken("user", new Object[] {user.getUserId(), targetUser.getUserNo()}));
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			} catch (UnsupportedEncodingException e) {
				result.put("message", "FAIL");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		// 일치하지 않는다면
		else{
			result.put("message", "Your password is NOT CORRECT");
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	// 사용자 전체 리스트 조회
	@GetMapping("/users")
	public ResponseEntity<?> userList(@RequestHeader HttpHeaders header) throws Exception {

		String token = header.get("access-token").toString();
		String requestUserId = jwtUtil.getUserIdAtToken(token);

		List<User> ulist = userService.selectUserAll();

		if(ulist==null || ulist.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<User>>(ulist, HttpStatus.OK);
	}

	// 로그아웃 기능
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session){
		session.invalidate();
		return new ResponseEntity<String>("Logout Complete", HttpStatus.OK);
	}



	// 팔로우
	@PostMapping("/follow")
	public ResponseEntity<?> follow(@RequestHeader HttpHeaders header, int to) throws Exception{
		String token = header.get("access-token").toString();
		int requestUserNo = jwtUtil.getUserNoAtToken(token);

		userService.follow(requestUserNo, to);
		return new ResponseEntity<String>("Following Complete",HttpStatus.CREATED);
	}

	// 언팔로우
	@DeleteMapping("/follow")
	public ResponseEntity<?> unfollow(@RequestHeader HttpHeaders header, int to) throws Exception{
		String token = header.get("access-token").toString();
		int requestUserNo = jwtUtil.getUserNoAtToken(token);

		userService.unFollow(requestUserNo, to);
		return new ResponseEntity<String>("Unfollow Complete", HttpStatus.OK);
	}

	// 팔로우 리스트 조회
	@GetMapping("/follow")
	public ResponseEntity<?> followlist(@RequestHeader HttpHeaders header) throws Exception{

		String token = header.get("access-token").toString();
		int requestUserNo = jwtUtil.getUserNoAtToken(token);
		List<Integer>[] flist = new List[2];

		List<Integer> myFollower = userService.myFollower(requestUserNo);
		List<Integer> myFollowing = userService.myFollowing(requestUserNo);

		flist[0] = myFollower;
		flist[1] = myFollowing;

		return new ResponseEntity<List<Integer>[]>(flist, HttpStatus.OK);
	}

}
