package com.linjw.myoa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.linjw.myoa.service.DepartmentService;
import com.linjw.myoa.service.ForumService;
import com.linjw.myoa.service.PrivilegeService;
import com.linjw.myoa.service.StationService;
import com.linjw.myoa.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



@SuppressWarnings({ "unchecked", "serial" })
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	
	// =============== ModelDriven的支持 ==================
	protected T model;
	//如果没有此处，则删除、添加、修改都不可行，因为找不到对应的类对象

	public BaseAction() {
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

	// =============== Service实例的声明 ==================
	@Resource
	protected StationService stationService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
	
}
