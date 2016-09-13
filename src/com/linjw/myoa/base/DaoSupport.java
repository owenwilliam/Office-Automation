package com.linjw.myoa.base;

import java.util.List;

import com.linjw.myoa.model.PageBean;
import com.linjw.myoa.util.QueryHelper;

public interface DaoSupport<T> {
	
	
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
	
	
	/**
	 * 公共的查询分页信息的方法
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param hql
	 *            查询数据列表的HQL
	 * @param parameters
	 *            参数列表，与HQL中问号一一对应
	 * @return
	 */

	PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters);
	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param queryHelper
	 *            HQL语句与参数列表
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

}
