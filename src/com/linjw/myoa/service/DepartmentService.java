package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.model.Department;

public interface DepartmentService {
	//查询所有
	public List<Department> findAll();
	//删除实体
	public void detele(Long id);
	//保存实体
	public void save(Department department);
	//通过ID查询实体
	public Department getById(Long id);
	//更新实体
	public void update(Department department);
	/**
	 * 查询顶级部门列表
	 * 
	 * @return
	 */
	public List<Department> findToList();
	/**
	 * 查询子部门列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Department> findChildren(Long parentId);

}
