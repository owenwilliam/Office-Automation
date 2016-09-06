package com.linjw.myoa.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.linjw.myoa.base.BaseDao;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	//使用反射技术得到T的真实类型
	@SuppressWarnings("unchecked")
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
	
	public void save(T entity){
		getSession().save(entity);
	}
	
	public void delete(long id){
		Object obj = getById(id);
		if(obj != null){
			getSession().delete(obj);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getById(long id){
		return (T) getSession().get(clazz,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByIds(long[] ids){
		return getSession().createQuery("from User where id in(:ids)").setParameter("ids",ids).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}
}
