package com.page.Dto;

import java.sql.*;

public class PDto {
	int pId;
	String userId;
	String pName;
	String pTTC;
	String pPrice;
	String pTradeMethod;
	String pPhoneNumber;
	String oriFile;
	String serFile;
	Timestamp pDate;
	
	public PDto() {}
	
	public PDto(String userId, String pName, String pTTC, String pPrice, String pTradeMethod, String pPhoneNumber,
			String oriFile, String serFile) {
		super();
		this.userId = userId;
		this.pName = pName;
		this.pTTC = pTTC;
		this.pPrice = pPrice;
		this.pTradeMethod = pTradeMethod;
		this.pPhoneNumber = pPhoneNumber;
		this.oriFile = oriFile;
		this.serFile = serFile;
	}
	
	public Timestamp getpDate() {
		return pDate;
	}
	public void setpDate(Timestamp tp) {
		this.pDate = tp;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpTTC() {
		return pTTC;
	}
	public void setpTTC(String pTTC) {
		this.pTTC = pTTC;
	}
	public String getpPrice() {
		return pPrice;
	}
	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}
	public String getpTradeMethod() {
		return pTradeMethod;
	}
	public void setpTradeMethod(String pTradeMethod) {
		this.pTradeMethod = pTradeMethod;
	}
	public String getpPhoneNumber() {
		return pPhoneNumber;
	}
	public void setpPhoneNumber(String pPhoneNumber) {
		this.pPhoneNumber = pPhoneNumber;
	}
	public String getOriFile() {
		return oriFile;
	}
	public void setOriFile(String oriFile) {
		this.oriFile = oriFile;
	}
	public String getSerFile() {
		return serFile;
	}
	public void setSerFile(String serFile) {
		this.serFile = serFile;
	}
	
}
