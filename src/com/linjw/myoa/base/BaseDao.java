package com.linjw.myoa.base;

import java.util.List;

public interface BaseDao<T> {
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(T entity);
	/**
	 * 删除实体
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 更新实体
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 按一个ID查询实体
	 * @param id
	 * @return
	 */
	public T getById(Long id);
	/**
	 * 按多个ID查询实体
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(Long[] ids);
	/**
	 * 查询所有的ID
	 * @return
	 */
	public List<T> findAll();

}
