package com.linjw.myoa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


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
System.out.println("clazz.getSimpleName()"+clazz.getSimpleName());
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}
}
