package com.linjw.myoa.model;

import java.util.Date;

public class NoticeInfo {
	
	private Long id;
	private String content;//内容
	private boolean roll;
	private Date postTime;
	private User author;
	private String title;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isRoll() {
		return roll;
	}
	public void setRoll(boolean roll) {
		this.roll = roll;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
