package com.linjw.myoa.view.action;

import java.util.ArrayList;
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
	
	
	private boolean asc = false;
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
		
		/*// // 准备分页信息 v1
		PageBean pageBean = topicService.getPageBeanByForum(pageNum, pageSize, forum);
		 ActionContext.getContext().getValueStack().push(pageBean);*/
		 //准备分页信息v2
		 String hql = "from Topic t where t.forum=? order by (case t.type when 2 then 2 else 0 end) desc,t.lastUpdateTime desc";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(forum);
		PageBean pageBean = topicService.getPageBean(pageNum, pageSize, hql, parameters);
		ActionContext.getContext().getValueStack().push(pageBean);
		 return "show";
	}

}

