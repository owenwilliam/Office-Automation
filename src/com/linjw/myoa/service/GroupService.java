package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.Group;
import com.linjw.myoa.model.User;

public interface GroupService extends DaoSupport<Group>{

	public List<Group> getByUser(User person);

}
