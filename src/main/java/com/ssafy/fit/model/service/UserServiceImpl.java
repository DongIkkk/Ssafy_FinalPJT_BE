package com.ssafy.fit.model.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.IUserDao;
import com.ssafy.fit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private IUserDao userDao;

	@Autowired
	public UserServiceImpl(IUserDao userDao) {
		this.userDao = userDao;
	}

	// 아이디로 유저 조회
	@Override
	public User selectUserByUserId(String userId) {
		return userDao.selectUserByUserId(userId);
	}

	// 회원가입 시 패스워드 체크
	public boolean isPassword(String userId, String password) {
		User user = selectUserByUserId(userId);

		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}


	// 아이디 중복 체크
	@Override
	public boolean isDuplicate(String userId) {

		User user = selectUserByUserId(userId);

		if (user != null) {
			return false;
		}
		return true;
	}

	// 아이디로 닉네임 가져오기
	@Override
	public String getNickNameByUserId(String userId) {
		return userDao.getNickNameByUserId(userId);
	}

	// 유저 추가
	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	// 비디오 찜 추가
	@Override
	public void likeVideo(String userId, int videoNo) {
		userDao.likeVideo(userId, videoNo);
	}

	// 비디오 찜 삭제
	@Override
	public void unLikeVideo(String userId, int videoNo) {
		userDao.unLikeVideo(userId, videoNo);
	}

	// 비디오 번호로 비디오 조회
	@Override
	public List<Integer> selectLikeVideoNumbers(String userId) {
		return userDao.selectLikeVideoNumbers(userId);
	}

	//유저 아이디로 유저 가져오기
	@Override
	public int getUserNoByUserId(String userId) {
		return userDao.getUserNoByUserId(userId);
	}

	//전체 유저 조회
	@Override
	public List<User> selectUserAll() {
		return userDao.selectUserAll();
	}

	//팔로워
	@Override
	public List<Integer> myFollower(int userNo) {
		return userDao.myFollower(userNo);
	}

	//팔로잉
	@Override
	public List<Integer> myFollowing(int userNo) {
		return userDao.myFollowing(userNo);
	}

	//팔로우 팔로잉 추가
	@Override
	public void follow(int from, int to) {
		userDao.follow(from, to);
	}

	//팔로우 팔로잉 삭제
	@Override
	public void unFollow(int from, int to) {
		userDao.unFollow(from, to);
	}

}
