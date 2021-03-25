package com.page.Dao;

import java.sql.*;
import java.util.*;

import com.page.Dto.*;

public class QDao {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "sck97";
	String pw = "skdltm97";
		
	
	public QDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // driver load
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void write(String qUserId,String qTitle,String qContent) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "insert into qanda (qId,qUserId,qTitle,qContent) values(qanda_seq.nextval,?,?,?)";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,qUserId);
			pstmt.setString(2,qTitle);
			pstmt.setString(3,qContent);
			int rn = pstmt.executeUpdate();
			
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
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<QDto> list(){
		
		ArrayList<QDto> dtos = new ArrayList<QDto>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			
			String query = "select qId,qUserId,qTitle,qContent,qDate from qanda";
			pstmt = connection.prepareStatement(query);
			resultSet = pstmt.executeQuery();
		
			while(resultSet.next()) {
				int qId = resultSet.getInt("qId");
				String qUserId = resultSet.getString("qUserID");
				String qTitle= resultSet.getString("qTitle");
				String qContent= resultSet.getString("qContent");
				Timestamp qDate = resultSet.getTimestamp("qDate");
			
				QDto dto = new QDto(qId,qUserId,qTitle,qContent,qDate);
				dtos.add(dto);
			}
			
			System.out.println("입력 성공");
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
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
public QDto contentView(String strID) {
		
		QDto	 dto = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "select qUserId, qTitle, qDate, qContent from qanda where qId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1,Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				//String bHit = 
				String qUserId = resultSet.getString("qUserId");
				String qTitle = resultSet.getString("qTitle");
				Timestamp tp = resultSet.getTimestamp("qDate");
				String qContent = resultSet.getString("qContent");
			
				dto = new QDto();
				dto.setqUserId(qUserId);
				dto.setqTitle(qTitle);
				dto.setqDate(tp);
				dto.setqContent(qContent);
				dto.setqId(Integer.parseInt(strID));
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
	
	public void modify(String qId, String qTitle, String qContent){
		Connection connection = null;
		PreparedStatement pstmt =null;
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		try {
			connection = DriverManager.getConnection(url,id,pw);
			
			String query = "update qanda set qTitle = ?,qContent = ? ,qDate = ? where qId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,qTitle);
			pstmt.setString(2,qContent);
			pstmt.setTimestamp(3, tp);
			pstmt.setInt(4, Integer.parseInt(qId));
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
	
	public void delete(String qId) {
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			System.out.println(qId);
			connection = DriverManager.getConnection(url,id,pw);
			String query = "delete from qanda where qId=?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(qId));
			int rn = pstmt.executeUpdate();
			
			if(rn == 1)
			{
				System.out.println("성공하였습니다.");
			}else {
				System.out.println("실패하였습니다.");
			}
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
	
	//bbsID 게시글 번호 가져오는 함수

	public int getNext() { 
		Connection connection = null;
		PreparedStatement pstmt =null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String SQL = "SELECT bId FROM qanda ORDER BY qId DESC";
			pstmt = connection.prepareStatement(SQL);
			resultSet = pstmt.executeQuery();	
			
			if(resultSet.next()) {
				return resultSet.getInt(1) + 1;
			}
			return 1;//첫 번째 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	public boolean nextPage (int pageNumber) {
		Connection connection = null;
		PreparedStatement pstmt =null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String SQL = "SELECT * FROM qanda WHERE qId < ? ORDER BY qId DESC LIMIT 9";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 9);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 		
	}
}