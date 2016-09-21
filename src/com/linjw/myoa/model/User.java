package com.linjw.myoa.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author 林剑文　　2014-7-8
 * 用户管理　
 *
 */
public class User {

	private Long id;
	private String loginName;//用户登录名
	private String password;//密码
	private String name;//用户真实姓名
	private String gender;//性别
	private String phoneNumber;//电话
	private String email;//邮箱
	private String description;//说明
	
	//关联
	private Department department;
	private Set<Station> stations = new HashSet<Station>();

	/**
	 * 判断本用户是否指定名称的权限
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		
		//超级管理员拥有所有的权取限
		if(isAdmin()){
			return true;
		}
		
		for(Station station:stations){
			for(Privilege priv :station.getPrivileges()){
				if(priv.getName().equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否有指定URL的权限
	 * @param privUrl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasPrivilegeByUrl(String privUrl){
		//超级管理员所有权限
		if(isAdmin()){
			return true;
		}
		//>>去掉后面的参数
		int pos = privUrl.indexOf("?");
		if(pos > -1){
			privUrl = privUrl.substring(0,pos);
		}
		//>>去掉UI后缀
		if(privUrl.endsWith("UI")){
			privUrl = privUrl.substring(0,privUrl.length()-2);
		}
		//如果URL不需要控制，则登录用户就可以使用。　　去最大的作用域中拿allPrivilegeUrls
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
	    if(!allPrivilegeUrls.contains(privUrl)){
	    	return true;
	    	
	    }else{
	    	//普通用户要判断是否含有这个权限
	    	for(Station station : stations){
	    		for(Privilege priv :station.getPrivileges()){
	    			if(privUrl.equals(priv.getUrl())){
	    				return true;
	    			}
	    		}
	    	}
	    	return  false;
	    }
	  }
	
	/**
	 * 判断本用户是否是超级管理员
	 * @return
	 */
	public boolean isAdmin(){
		return "admin".equals(loginName);
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Station> getStations() {
		return stations;
	}
	public void setStations(Set<Station> stations) {
		this.stations = stations;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
}
