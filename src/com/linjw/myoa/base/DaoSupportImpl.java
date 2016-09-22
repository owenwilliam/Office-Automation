package com.linjw.myoa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.linjw.myoa.model.PageBean;
import com.linjw.myoa.util.QueryHelper;


//@Transactional注解可以被继承
//@Transactional注解对父类中声明的方法无效
@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	/**
	 * 使用反射技术得到T的真实类型
	 */
	public DaoSupportImpl(){
		
		ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();//获取当前new的对象的泛型的父类类型
	     this.clazz = (Class<T>)pt.getActualTypeArguments()[0];//获取第一个类型参数的真实类型
	    System.out.println("clazz --->"+clazz);
	}
	/**
	 * 获取当前可用的Session
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 保存实体
	 */
	public void save(T entity){
		getSession().save(entity);
	}
	/**
	 * 更新实体
	 */
	public void update(T entity){
		getSession().update(entity);
	}
	/**
	 * 删除实体
	 */
	public void delete(Long id){
		Object obj = getById(id);
		if(obj != null){
			getSession().delete(obj);
		}
	}
	
	/**
	 * 按一个ID查询实体
	 */
	public T getById(Long id){
		if(id == null){
			return null;
		}else{
		return (T) getSession().get(clazz,id);
		}
	}
	
	/**
	 * 按多个ID查询实体
	 */
	public List<T> getByIds(Long[] ids){
		if(ids == null || ids.length == 0){
			return Collections.EMPTY_LIST;
		}else{
		return getSession().createQuery(
		"from "+clazz.getSimpleName()+" where id in (:ids)")
		.setParameterList("ids",ids)
		.list();

		}
	}
	/**
	 * 查询所有的ID
	 */
	public List<T> findAll(){
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}
	
	
	/**
	 *  公共的查询分页信息的方法(non-Javadoc)
	 * @see com.linjw.myoa.base.DaoSupport#getPageBean(int, int, java.lang.String, java.util.List)
	 */
	@Deprecated
		public PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters) {
			System.out.println("-------> DaoSupportImpl.getPageBean()");

			// 查询本页的数据列表
			Query listQuery = getSession().createQuery(hql); // 创建查询对象
			if (parameters != null) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					listQuery.setParameter(i, parameters.get(i));
				}
			}
			listQuery.setFirstResult((pageNum - 1) * pageSize);
			listQuery.setMaxResults(pageSize);
			List list = listQuery.list(); // 执行查询

			// 查询总记录数量
			Query countQuery = getSession().createQuery("SELECT COUNT(*) " + hql);
			if (parameters != null) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					countQuery.setParameter(i, parameters.get(i));
				}
			}
			Long count = (Long) countQuery.uniqueResult(); // 执行查询

			return new PageBean(pageNum, pageSize, count.intValue(), list);
		}
		
		
		// 公共的查询分页信息的方法（最终版）
		public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {
		//	System.out.println("-------> DaoSupportImpl.getPageBean( int pageNum, int pageSize, QueryHelper queryHelper )");

			// 参数列表
			List<Object> parameters = queryHelper.getParameters();

			// 查询本页的数据列表
			Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
			if (parameters != null) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					listQuery.setParameter(i, parameters.get(i));
				}
			}
			listQuery.setFirstResult((pageNum - 1) * pageSize);
			listQuery.setMaxResults(pageSize);
			List list = listQuery.list(); // 执行查询

			// 查询总记录数量
			Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
			if (parameters != null) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					countQuery.setParameter(i, parameters.get(i));
				}
			}
			Long count = (Long) countQuery.uniqueResult(); // 执行查询

			return new PageBean(pageNum, pageSize, count.intValue(), list);
		}
}
