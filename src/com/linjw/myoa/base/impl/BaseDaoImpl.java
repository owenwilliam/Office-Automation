package com.linjw.myoa.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.linjw.myoa.base.BaseDao;
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	/**
	 * 使用反射技术得到T的真实类型
	 */
	public BaseDaoImpl(){
		
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
System.out.println("id="+id);
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
		return getSession().createQuery("from User where id in(:ids)").setParameter("ids",ids).list();
	}
	
	/**
	 * 查询所有的ID
	 */
	public List<T> findAll(){
System.out.println("clazz.getSimpleName()"+clazz.getSimpleName());
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}
}
