package com.linjw.myoa.view.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.BaseAction;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.model.Reply;
import com.linjw.myoa.model.Topic;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class TopicAction extends BaseAction<Topic>{
	private Long forumId;
	
	
	/**
	 * 显示单个主题（主帖+回帖列表）
	 */
	public String show(){
		//准备数据　：　topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic",topic);
		//准备数据：replyList
		List<Reply> replyList = replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replyList);
		return "show";
	}
  /**
   * 添加界面 ----发布新帖
   */
	public String addUI()throws Exception{
		//准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum",forum);
		return "addUI";
	}
	/**
	 * 添加----发布
	 * @return
	 * @throws Exception
	 */
	public String add()throws Exception{
		// 封装
		// >> 表单参数，已经封装了title, content
		model.setForum(forumService.getById(forumId));
		// >> 当前直接获取的信息
		model.setAuthor(getCurrentUser());//获得当前的用户
		model.setiPAddr(ServletActionContext.getRequest().getRemoteAddr());//获取当前的ip地址
		model.setPostTime(new Date());
		//保存
		topicService.save(model);
		return "toShow";// 转到新主题的显示页面
	}
	//---
public Long getForumId() {
	return forumId;
}
public void setForumId(Long forumId) {
	this.forumId = forumId;
}
}
