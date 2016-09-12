package com.linjw.myoa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.BaseAction;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.model.PageBean;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ForumAction extends BaseAction<Forum>{
	/**
	 * 版块列表
	 */
	public String list()throws Exception{
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList",forumList);
		return "list";
	}
	/**
	 * 显示单个模块（主题板块）
	 */
	
	public String show()throws Exception{
		//准备数据forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum",forum);
		
	/*	//准备数据：topicList
		List<Topic> topicList = topicService.findByForum(forum);
		ActionContext.getContext().put("topicList",topicList);*/
		
		// // 准备分页信息 v1
		PageBean pageBean = topicService.getPageBeanByForum(pageNum, pageSize, forum);
		 ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}
}

