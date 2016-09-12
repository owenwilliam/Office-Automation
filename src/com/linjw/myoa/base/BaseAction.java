package com.linjw.myoa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.linjw.myoa.model.User;
import com.linjw.myoa.service.DepartmentService;
import com.linjw.myoa.service.ForumService;
import com.linjw.myoa.service.PrivilegeService;
import com.linjw.myoa.service.ReplyService;
import com.linjw.myoa.service.StationService;
import com.linjw.myoa.service.TopicService;
import com.linjw.myoa.service.UserService;
import com.opensymphony.xwork2.ActionContext;
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
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	
	/**
	 * 获取当前登录的用户
	 */
	protected User getCurrentUser(){
		return(User)ActionContext.getContext().getSession().get("user");
	}
	
	//==========================分布用的参数============================
	protected int pageNum = 1;//当前页
	protected int pageSize = 10;//每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
