package com.page.Dao;
import java.sql.*;
import java.util.*;

import com.page.Dto.*;

public class PDao {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "sck97";
	String pw = "skdltm97";
		
	public PDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // driver load
		 }catch(Exception e){
			e.printStackTrace();
		}
	}
	
public PDto contentView(String strID) {
		
		PDto	 dto = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "select userId, pName, pTTC,pPrice,pTradeMethod,pPhoneNumber,pDate,oriFile,serFile from plist where pId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1,Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				String pId = strID;
				String userId = resultSet.getString("userId");
				String pName = resultSet.getString("pName");
				String pTTC = resultSet.getString("pTTC");
				String pPrice = resultSet.getString("pPrice");
				String pTradeMethod = resultSet.getString("pTradeMethod");
				String pPhoneNumber = resultSet.getString("pPhoneNumber");
				Timestamp tp = resultSet.getTimestamp("pDate");
				String oriFile = resultSet.getString("oriFile");
				String serFile = resultSet.getString("serFile");
				
				dto = new PDto(userId, pName, pTTC,pPrice,pTradeMethod,pPhoneNumber,oriFile,serFile);
				dto.setpDate(tp);
				dto.setpId(Integer.parseInt(pId));
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

public ArrayList<PDto> list(){
	
	ArrayList<PDto> dtos = new ArrayList<PDto>();
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	
	try {
		connection = DriverManager.getConnection(url,id,pw);
		
		String query = "select pId, pName, pTTC, pPrice, oriFile from plist";
		pstmt = connection.prepareStatement(query);
		resultSet = pstmt.executeQuery();
	
		while(resultSet.next()) {
			int pId = resultSet.getInt("pId");
			String pName = resultSet.getString("pName");
			String pTTC= resultSet.getString("pTTC");
			String pPrice= resultSet.getString("pPrice");
			String oriFile= resultSet.getString("oriFile");
		   
			PDto dto = new PDto();
			dto.setpId(pId);
			dto.setpName(pName);
			dto.setpTTC(pTTC);
			dto.setpPrice(pPrice);
			dto.setOriFile(oriFile);
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
	
	public void uploadFile(PDto dto) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String query = "insert into plist (pId,userId,pName,pTTC,pPrice,pTradeMethod,pPhoneNumber, oriFile,serFile,pDate) values(plist_seq.nextval,?,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getpName());
			pstmt.setString(3, dto.getpTTC());
			pstmt.setString(4, dto.getpPrice());
			pstmt.setString(5, dto.getpTradeMethod());
			pstmt.setString(6, dto.getpPhoneNumber());
			pstmt.setString(7, dto.getOriFile());
			pstmt.setString(8, dto.getSerFile());
			pstmt.setTimestamp(9, tp);
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
	
	public void modify(String pId, PDto dto){
		Connection connection = null;
		PreparedStatement pstmt =null;
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		try {
			connection = DriverManager.getConnection(url,id,pw);
			
			String query = "update plist set pName = ?, pTTC=?,pPrice=?,pTradeMethod=?,pPhoneNumber=?, oriFile=?,serFile=?,pDate=? where pId = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getpName());
			pstmt.setString(2,dto.getpTTC());
			pstmt.setString(3, dto.getpPrice());
			pstmt.setString(4, dto.getpTradeMethod());
			pstmt.setString(5, dto.getpPhoneNumber());
			pstmt.setString(6, dto.getOriFile());
			pstmt.setString(7, dto.getSerFile());
			pstmt.setTimestamp(8,tp);
			pstmt.setInt(9,Integer.parseInt(pId));
			int rn = pstmt.executeUpdate();
			if(rn == 1)
			{
				System.out.println("업데이트에 성공하였습니다.");
			}
			else
			{
				System.out.println("업데이트에 실패 하였습니다.");
				
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
	
	public int getNext() { 
		Connection connection = null;
		PreparedStatement pstmt =null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String SQL = "SELECT pId FROM plist ORDER BY pId DESC";
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
	
	public void delete(String pId) {
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			System.out.println(pId);
			connection = DriverManager.getConnection(url,id,pw);
			String query = "delete from plist where pId=?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(pId));
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
	
	public boolean nextPage (int pageNumber) {
		Connection connection = null;
		PreparedStatement pstmt =null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(url,id,pw);
			String SQL = "SELECT pId FROM plist WHERE pId < ? ORDER BY pId DESC LIMIT 9";
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
