package com.linjw.myoa.model;

import java.util.Date;

/**
 * 文章
 * @author 林剑文　2014-7-13
 *
 */
public class Article {
	private Long id;
	private String title;//标题
	private String content;//内容
	private Date postTime;//作者
	private User author;//发表时间
	private String iPAddr;//发表文章所用的IP地址
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getiPAddr() {
		return iPAddr;
	}
	public void setiPAddr(String iPAddr) {
		this.iPAddr = iPAddr;
	}
	

}
