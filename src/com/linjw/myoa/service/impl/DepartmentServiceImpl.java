package com.linjw.myoa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.linjw.myoa.base.DaoSupportImpl;
import com.linjw.myoa.model.Department;
import com.linjw.myoa.service.DepartmentService;
@Service
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {
	
	@Resource
	private SessionFactory sessionFactory ;

	
	/**查找顶级与子级*/
	
	public List<Department> findToList() {
		return sessionFactory.getCurrentSession().createQuery("from Department d where d.parent is null").list();
	}
	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery("from Department d where d.parent.id=?").setParameter(0,parentId).list();
	}

}
