package com.page.Dao;

import java.sql.*;
import java.util.*;

import com.page.Dto.*;

public class BDao {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "sck97";
	String pw = "skdltm97";
		
	
	public BDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // driver load
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void write(String bUserId,String bTitle,String bContent) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "insert into mvc_board (bId,bUserId,bTitle,bContent,bGroup,bStep,bIndent) values(mvc_board_seq.nextval,?,?,?,mvc_board_seq.currval,0,0)";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,bUserId);
			pstmt.setString(2,bTitle);
			pstmt.setString(3,bContent);
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
	
	public ArrayList<BDto> list(){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			
			String query = "select bId,bUserId,bTitle,bContent,bDate,bGroup,bStep,bIndent from mvc_board";
			pstmt = connection.prepareStatement(query);
			resultSet = pstmt.executeQuery();
		
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bUserId = resultSet.getString("bUserID");
				String bTitle= resultSet.getString("bTitle");
				String bContent= resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bGroup= resultSet.getInt("bGroup");
				int bStep= resultSet.getInt("bStep");
				int bIndent= resultSet.getInt("bIndent");
			
				BDto dto = new BDto(bId,bUserId,bTitle,bContent,bDate,bGroup,bStep,bIndent);
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
	
public BDto contentView(String strID) {
		
		BDto	 dto = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "select bUserId, bTitle, bDate, bContent from mvc_board where bId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,strID);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				//String bHit = 
				String bUserId = resultSet.getString("bUserId");
				String bTitle = resultSet.getString("bTitle");
				Timestamp tp = resultSet.getTimestamp("bDate");
				String bContent = resultSet.getString("bContent");
			
				dto = new BDto();
				dto.setbUserId(bUserId);
				dto.setbTitle(bTitle);
				dto.setbDate(tp);
				dto.setbContent(bContent);
				dto.setbId(Integer.parseInt(strID));
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
	
	public void modify(String bId, String bTitle, String bContent){
		Connection connection = null;
		PreparedStatement pstmt =null;
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		try {
			connection = DriverManager.getConnection(url,id,pw);
			
			String query = "update mvc_board set bTitle = ?,bContent = ? ,bDate = ? where bId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,bTitle);
			pstmt.setString(2,bContent);
			pstmt.setTimestamp(3, tp);
			pstmt.setInt(4, Integer.parseInt(bId));
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
	
	public void delete(String bId) {
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			System.out.println(bId);
			connection = DriverManager.getConnection(url,id,pw);
			String query = "delete from mvc_board where bId=?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
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
			String SQL = "SELECT bId FROM mvc_board ORDER BY bId DESC";
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
			String SQL = "SELECT * FROM mvc_board WHERE bId < ? ORDER BY bId DESC LIMIT 10";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 10);
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