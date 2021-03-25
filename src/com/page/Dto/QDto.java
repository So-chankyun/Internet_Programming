package com.page.Dto;

import java.sql.*;

public class QDto {
	int qId;
	String qUserId;
	String qTitle;
	String qContent;
	Timestamp qDate;
	
	public QDto() {
		
	}

	public QDto(int qId, String qUserId, String qTitle, String qContent, Timestamp qDate) {
		super();
		this.qId = qId;
		this.qUserId = qUserId;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qDate = qDate;
	}



	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getqUserId() {
		return qUserId;
	}

	public void setqUserId(String qUserId) {
		this.qUserId = qUserId;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public Timestamp getqDate() {
		return qDate;
	}

	public void setqDate(Timestamp qDate) {
		this.qDate = qDate;
	}

}