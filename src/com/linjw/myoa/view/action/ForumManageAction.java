package com.linjw.myoa.view.action;



import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Forum;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ForumManageAction extends ModelDrivenBaseAction<Forum>{
	/** 列表显示*/
	public String list() throws Exception{
	 List<Forum> forumList = forumService.findAll();
	 ActionContext.getContext().put("forumList",forumList);
		return "list";
	}
	/**删除*/
	public String delete()throws Exception{
		forumService.delete(model.getId());
		return "toList";
	}
	/**添加页面*/
	public String addUI()throws Exception{
		return "addUI";
	}
	/**添加成功后*/
	public String add() throws Exception{
	forumService.save(model);
		return "toList";
	}
	/**修改页面*/
	public String editUI()throws Exception{
		//回显数据
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "editUI";
	}
	/**修改*/
	public String edit()throws Exception{
		//从数据库中取出原对象
		Forum forum = forumService.getById(model.getId());
		//设置要修改的属性
		  forum.setName(model.getName());
		  forum.setDescription(model.getDescription());
		  //更新到数据库
		  forumService.update(forum);
		return "toList";
	}
	
	/**
	 * 向上移动
	 */
	public String moveUp()throws Exception{
		forumService.moveUp(model.getId());
		return "toList";
	}
	/**
	 * 向下移动
	 */
	public String moveDown()throws Exception{
		forumService.moveDown(model.getId());
		return "toList";
	}
	
}
