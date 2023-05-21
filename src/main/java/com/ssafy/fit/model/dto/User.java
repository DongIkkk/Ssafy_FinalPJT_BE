package com.ssafy.fit.model.dto;

public class User {

	private int userNo;
	private String userId;
	private String password;
	private String userName;
	private String profileImgFullpath;
	private String profileImgName;
	private String email;
	private String gender;
	private int age;

	public User() {
	}

	public User(int userNo, String userId, String password, String userName,String profileImgFullpath,String profileImgName,String email, String gender, int age) {
		this.userNo = userNo;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.profileImgFullpath = profileImgFullpath;
		this.profileImgName = profileImgName;
		this.email = email;
		this.gender = gender;
		this.age = age;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfileImgFullpath() {
		return profileImgFullpath;
	}

	public void setProfileImgFullpath(String profileImgFullpath) {
		this.profileImgFullpath = profileImgFullpath;
	}

	public String getProfileImgName() {
		return profileImgName;
	}

	public void setProfileImgName(String profileImgName) {
		this.profileImgName = profileImgName;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"userNo=" + userNo +
				", userId='" + userId + '\'' +
				", password='" + password + '\'' +
				", userName='" + userName + '\'' +
				", profileImgFullpath='" + profileImgFullpath + '\'' +
				", profileImgName='" + profileImgName + '\'' +
				", email='" + email + '\'' +
				", gender='" + gender + '\'' +
				", age=" + age +
				'}';
	}
}
