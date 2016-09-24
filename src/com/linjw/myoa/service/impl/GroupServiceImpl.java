package com.linjw.myoa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.linjw.myoa.base.DaoSupportImpl;
import com.linjw.myoa.model.Group;
import com.linjw.myoa.model.User;
import com.linjw.myoa.service.GroupService;
@Service
@SuppressWarnings("unchecked")
public class GroupServiceImpl extends DaoSupportImpl<Group> implements GroupService{

	public List<Group> getByUser(User person) {
		return getSession().createQuery(
				"FROM Group g WHERE g.person=?")
				.setParameter(0,person)
				.list();
	}

}
