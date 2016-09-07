package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.model.Department;

public interface DepartmentService {
	//查询所有
	public List<Department> findAll();
	//删除实体
	public void detele(long id);
	//保存实体
	public void save(Department department);
	//通过ID查询实体
	public Department getById(long id);
	//更新实体
	public void update(Department department);

}
