package com.page.Dto;

import java.sql.*;

public class BDto {
	int bId;
	String bUserId;
	String bTitle;
	String bContent;
	Timestamp bDate;
	int bGroup;
	int bStep;
	int bIndent;
	
	public BDto() {
		
	}

	public BDto(int bId, String bUserId, String bTitle, String bContent, Timestamp bDate, int bGroup, int bStep, int bIndent) {

		this.bId = bId;
		this.bUserId = bUserId;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbUserId() {
		return bUserId;
	}

	public void setbUserId(String bUserId) {
		this.bUserId = bUserId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
}
