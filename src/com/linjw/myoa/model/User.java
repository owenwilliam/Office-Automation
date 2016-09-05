package com.linjw.myoa.model;
/**
 * 
 * @author 林剑文　　2014-7-6
 * @hibernate.class table="t_user"
 *
 */
public class User {
	/**
	 * @hibernate.id
	 *       generator-class="native"
	 */
	private long id;
	/**
	 * @hibernate.property
	 */
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
