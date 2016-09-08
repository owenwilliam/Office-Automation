package com.linjw.myoa.model;

import java.util.HashSet;
import java.util.Set;

/**
 *岗位管理的类
 * @author 林剑文 2014-7-7
 *
 */
public class Station {
	
      private Long id;
      private String name;
      private String description;
      
      //关联
      private Set<User> users = new HashSet<User>();
      
      

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
