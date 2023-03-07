package com.library.api.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Issue {

	private Integer id;

	private Integer memberId;

	private String bookName;

	private Date issue_date;

	private String status = "ISSUED"; // Use Enums in future
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBook_id(String bookName) {
		this.bookName = bookName;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
