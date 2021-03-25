package com.page.Dto;

import java.sql.*;

public class userInfoDto {
	String userId;
	String userPw;
	String userName;
	String userGender;
	String userPhoneNumber;
	String usereMail;
	Timestamp userRdate;
	
//	public userInfoDto() {}
//	
//	public userInfoDto(String userId, String userPw, String userName, String userGender, String userPhoneNumber,
//			String usereMail) {
//		super();
//		this.userId = userId;
//		this.userPw = userPw;
//		this.userName = userName;
//		this.userGender = userGender;
//		this.userPhoneNumber = userPhoneNumber;
//		this.usereMail = usereMail;
//	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUsereMail() {
		return usereMail;
	}
	public void setUsereMail(String usereMail) {
		this.usereMail = usereMail;
	}
	public Timestamp getUserRdate() {
		return userRdate;
	}
	public void setUserRdate(Timestamp userRdate) {
		this.userRdate = userRdate;
	}
	
	
}
