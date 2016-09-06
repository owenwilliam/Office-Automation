package com.linjw.myoa.base;

import org.junit.Test;

import com.linjw.myoa.dao.RoleDao;
import com.linjw.myoa.dao.UserDao;
import com.linjw.myoa.dao.impl.RoleDaoImpl;
import com.linjw.myoa.dao.impl.UserDaoImpl;

public class BaseDaoTest {

	@Test
	public void testSave() {
		UserDao userDao = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}
}
