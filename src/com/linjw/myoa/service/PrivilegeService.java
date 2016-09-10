package com.linjw.myoa.service;

import java.util.Collection;
import java.util.List;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege>{

	/**
	 * 查询所有顶级的权根
	 */
	public List<Privilege> findTopList();

	public Collection<String> getAllPrivilegeurls();
}
