package com.page.Dao;

import java.sql.*;
import java.util.*;

import com.page.Dto.*;

public class userInfoDao {
	
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIR = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "sck97";
	String pw = "skdltm97";
		
	
	public userInfoDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // driver load
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public int grantLogin(String userId, String userPw) {
		int ri = 0;
		String dbPw;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "select pw from userinfo where id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userId);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				dbPw = resultSet.getString("userpassword");
				if(dbPw.equals(userPw)) {
					ri = userInfoDao.MEMBER_LOGIN_SUCCESS; // 성공
				}else {
					ri = userInfoDao.MEMBER_LOGIN_PW_NO_GOOD; // 비밀번호가 잘못됨
				}
			}else {
				ri = userInfoDao.MEMBER_LOGIN_IS_NOT; // 회원이 아님
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(resultSet != null) {
						resultSet.close();
					}
				}catch(Exception e2) {}
			}
		
		return ri;
	}
	
public userInfoDto userInfoView(String userId) {
		
		userInfoDto dto = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "select username, usergender, userphonenumber, useremail from userinfo where userId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,userId);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				//String bHit = 
				String userName = resultSet.getString("userName");
				String userGender = resultSet.getString("userGender");
				String userPhoneNumber = resultSet.getString("userPhoneNumber");
				String usereMail = resultSet.getString("usereMail");
			
				dto = new userInfoDto();
				dto.setUserName(userName);
				dto.setUserGender(userGender);
				dto.setUserPhoneNumber(userPhoneNumber);
				dto.setUsereMail(usereMail);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(resultSet != null) {
						resultSet.close();
					}
					if(connection != null) {
						connection.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		return dto;
		}
	
	public int userCheck(String userId, String userPw) {
		int ri = 0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String query = "select userpw from userinfo where userid = ?";
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userId);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				dbPw = resultSet.getString("userpw");
				if(dbPw.equals(userPw)) {
					ri = userInfoDao.MEMBER_LOGIN_SUCCESS; // 성공
				}else {
					ri = userInfoDao.MEMBER_LOGIN_PW_NO_GOOD; // 비밀번호가 잘못됨
				}
			}else {
				ri = userInfoDao.MEMBER_LOGIN_IS_NOT; // 회원이 아님
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(resultSet != null) {
						resultSet.close();
					}
				}catch(Exception e2) {}
		}
		
		return ri;
	}
	
	public void delete(String userId) {
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "delete from userInfo where userId=?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userId);
			int rn = pstmt.executeUpdate();
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection !=null) {
					connection.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void modify(String userId, String userName, String userPhoneNumber, String usereMail){
		Connection connection = null;
		PreparedStatement pstmt =null;
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		try {
			connection = DriverManager.getConnection(url,id,pw);
			
			String query = "update userInfo set userName = ?,userPhoneNumber = ?, usereMail = ? where userId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,userName);
			pstmt.setString(2,userPhoneNumber);
			pstmt.setString(3,usereMail);
			pstmt.setString(4, userId);
			int rn = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection !=null) {
					connection.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void loginTime(String userId) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		String query = "update userInfo set userrDate = ? where userId = ?";
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			pstmt = connection.prepareStatement(query);
			pstmt.setTimestamp(1,tp);
			pstmt.setString(2,userId);
			int ri = pstmt.executeUpdate(); // BOF를 처음에는 가리키고 있음, 만약 찾는게 있다면 next가 존재할것이다.
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {
					pstmt.close();	
				}
				if(connection != null) {
					connection.close();	
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public int confirmId(String userId) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String query = "select userid from userinfo where userid = ?";
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,userId);
			resultSet = pstmt.executeQuery(); // BOF를 처음에는 가리키고 있음, 만약 찾는게 있다면 next가 존재할것이다.
			
			if(resultSet.next()) {
				ri = userInfoDao.MEMBER_EXISTENT;
			}else {
				ri = userInfoDao.MEMBER_NONEXISTENT;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) {
					resultSet.close();		
				}
				if(pstmt !=null) {
					pstmt.close();	
				}
				if(connection != null) {
					connection.close();	
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int insertUserInfo(userInfoDto dto) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into userinfo values(?,?,?,?,?,?,?)";
		 
		try {
			connection = DriverManager.getConnection(url,id,pw);
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPw());
			pstmt.setString(3, dto.getUserName());
			pstmt.setString(4, dto.getUserGender());
			pstmt.setString(5, dto.getUserPhoneNumber());
			pstmt.setString(6, dto.getUsereMail());
			pstmt.setTimestamp(7, dto.getUserRdate());
			pstmt.executeUpdate();
			ri = userInfoDao.MEMBER_JOIN_SUCCESS;
			System.out.println(dto.getUserName());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public ArrayList<userInfoDto> displayInfo() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		userInfoDto dto = null;
		ArrayList <userInfoDto> alluser = new ArrayList<userInfoDto>();
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "select userId, username, userGender,userPhoneNumber,usereMail from userinfo";
			pstmt = connection.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) { 
				String userId = resultSet.getString("userId");
				String name = resultSet.getString("username"); 
				String  gender= resultSet.getString("usergender"); 
				String phoneNumber = resultSet.getString("userphoneNumber"); 
				String eMail = resultSet.getString("useremail"); 
				
				dto = new userInfoDto();
				dto.setUserId(userId);
				dto.setUserName(name);
				dto.setUserGender(gender);
				dto.setUserPhoneNumber(phoneNumber);
				dto.setUsereMail(eMail);
				alluser.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}		
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return alluser;
	}
}
