package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.dto.User;

public interface IUserDao {

	public List<User> selectUserAll();

	public User selectUserByUserId(String userId);

	public boolean isDuplicate(String userId);

	public String getNickNameByUserId(String userId);

	public int insertUser(User user);

	public void likeVideo(String userId, int videoNo);

	public void unLikeVideo(String userId, int videoNo);

	public List<Integer> selectLikeVideoNumbers(String userId);

	public int getUserNoByUserId(String userId);

	public List<Integer> myFollower(int userNo);
	
	public List<Integer> myFollowing(int userNo);
	
	public void follow(int from , int to);
	
	public void unFollow(int from, int to);
	
}
