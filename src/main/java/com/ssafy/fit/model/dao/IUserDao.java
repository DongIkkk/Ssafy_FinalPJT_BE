package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.dto.User;

public interface IUserDao {


	public List<User> selectUserAll();

	public User selectUserByUserId(String userId);

	public int insertUser(User user);

	public List<Integer> myFollower(int userNo);

	public List<Integer> myFollowing(int userNo);

	public void follow(int from , int to);

	public void unFollow(int from, int to);
	
}
