package com.linjw.myoa.base;

import java.lang.reflect.ParameterizedType;


import com.opensymphony.xwork2.ModelDriven;



@SuppressWarnings({ "unchecked", "serial" })
public abstract class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T> {

	
	// =============== ModelDriven的支持 ==================
	protected T model;
	//如果没有此处，则删除、添加、修改都不可行，因为找不到对应的类对象

	public ModelDrivenBaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public T getModel() {
		return model;
	}

	
	
}
