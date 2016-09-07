package com.linjw.myoa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linjw.myoa.dao.DepartmentDao;
import com.linjw.myoa.model.Department;
import com.linjw.myoa.service.DepartmentService;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DepartmentDao departmentDao;

	//列表
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
   //删除
	public void detele(long id) {
		departmentDao.delete(id);

	}
   //保存
	public void save(Department department) {
		departmentDao.save(department);

	}
   //查找
	public Department getById(long id) {
		return departmentDao.getById(id);
	}
	//更新
	public void update(Department department) {
       departmentDao.update(department);
	}

}
