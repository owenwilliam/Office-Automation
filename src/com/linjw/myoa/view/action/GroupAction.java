package com.linjw.myoa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Group;
import com.linjw.myoa.model.User;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class GroupAction extends ModelDrivenBaseAction<Group>{
	/** 列表 */
	public String list() throws Exception {
	  List<Group> groupList = groupService.getByUser(userService.getById(getCurrentUser().getId()));
	  ActionContext.getContext().put("groupList", groupList);
		return "list";    
		}

	/** 删除 */
	public String delete() throws Exception { 
		groupService.delete(model.getId());
		return "toList"; 
		}

	/** 添加页面 */
	public String addUI() throws Exception { 
		
		return "addUI";
		}

	/** 添加 */
	public String add() throws Exception { 
		User person = userService.getById(getCurrentUser().getId());
		model.setPerson(person);
		model.setCount(0);
		groupService.save(model);
		return "toList"; 
		}

	/** 修改页面 */
	public String editUI() throws Exception { 
		Group group = groupService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(group);
		return "editUI"; 
		}

	/** 修改 */
	public String edit() throws Exception { 
		Group group = groupService.getById(model.getId());
		group.setName(model.getName());
		group.setDescription(model.getDescription());
		groupService.update(group);
		return "toList"; 
		}

}
