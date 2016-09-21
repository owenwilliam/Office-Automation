package com.linjw.myoa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Reply;
import com.linjw.myoa.model.Topic;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ReplyAction extends ModelDrivenBaseAction<Reply>{

	private Long topicId;

	 /**
	  * 发表新回复页面
	  * @return
	  */
	public String addUI()throws Exception{
		//准备数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic",topic);
		return "addUI";
	}
	/**
	 * 发表新回复
	 * @return
	 */
	public String add()throws Exception{
		//封装
		//>>表单字段，已经封装了title/content
		model.setTopic(topicService.getById(topicId));
		//>>当前信息
		model.setAuthor(getCurrentUser());
		model.setiPAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		//保存
		replyService.save(model);
		return "toTopicShow";// 转到新回复所在主题的显示页面
	}
	/**
	 * 删除回复
	 * @return
	 */
	public String delete()throws Exception{
		System.out.println("model.getId()"+model.getId()); 
		replyService.delete(model.getId());
		return "delete";
	}
	//---
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
}
