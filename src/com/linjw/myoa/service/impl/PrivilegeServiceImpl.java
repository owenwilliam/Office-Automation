package com.linjw.myoa.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.linjw.myoa.base.DaoSupportImpl;
import com.linjw.myoa.model.Privilege;
import com.linjw.myoa.service.PrivilegeService;
@Service
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {

	
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"from Privilege p where p.parent is null")//
				.list();
	}
//查找URL的值不为空的就显示
	public Collection<String> getAllPrivilegeurls() {
		return getSession().createQuery(
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")
				.list();
	}


}
