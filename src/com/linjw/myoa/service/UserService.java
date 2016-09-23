package com.linjw.myoa.service;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.User;


public interface UserService extends DaoSupport<User>{
/**
 * 根据登录名与密码查询用户
 * @param loginName
 * @param password　明文密码
 * @return
 */
	public User findByLoginNameAndPassword(String loginName, String password);

public User findByUserLoginName(String loginName);

}
