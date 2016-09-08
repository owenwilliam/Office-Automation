package com.linjw.myoa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linjw.myoa.dao.DepartmentDao;
import com.linjw.myoa.model.Department;
import com.linjw.myoa.service.DepartmentService;
@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DepartmentDao departmentDao;
	@Resource
	private SessionFactory sessionFactory ;

	//列表
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
   //删除
	public void detele(Long id) {
		departmentDao.delete(id);

	}
   //保存
	public void save(Department department) {
		departmentDao.save(department);

	}
   //查找
	public Department getById(Long id) {
		return departmentDao.getById(id);
	}
	//更新
	public void update(Department department) {
       departmentDao.update(department);
	}
	
	/**查找顶级与子级*/
	
	public List<Department> findToList() {
		return sessionFactory.getCurrentSession().createQuery("from Department d where d.parent is NULL").list();
	}
	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery("from Department d where d.parent.id=?").setParameter(0,parentId).list();
	}

}
