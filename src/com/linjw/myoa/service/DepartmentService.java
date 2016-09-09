package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.Department;


public interface DepartmentService extends DaoSupport<Department> {
	
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
