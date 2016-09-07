package com.linjw.myoa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.model.Department;
import com.linjw.myoa.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	
	@Resource
	private DepartmentService departmentService;
	
	private Department model = new Department();
	
	public Department getModel() {
		
		return model;
	}

	/**
	 * 列表
	 */
	public String list()throws Exception{
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList",departmentList);
		return "list";
	}
	/**
	 * 删除
	 */
	public String delete()throws Exception{
		departmentService.detele(model.getId());
		return "toList";
	}
	/**
	 * 添加页面
	 */
	public String addUI()throws Exception{
		return "addUI";
	}
	/**
	 * 添加成功后
	 */
	public String add()throws Exception{
		departmentService.save(model);
		return "toList";
	}
	/**
	 * 修改页面
	 */
	
	public String editUI()throws Exception{
		//准备回显数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		return "editUI";
	}
	/**
	 * 修改
	 */
	public String edit()throws Exception{
		//从数据库获得对象
		Department department = departmentService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		departmentService.update(department);
		//修改对象
		return "toList";
	}

}
