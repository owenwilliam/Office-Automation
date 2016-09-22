package com.linjw.myoa.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.model.PageBean;
import com.linjw.myoa.model.Reply;
import com.linjw.myoa.model.Topic;
import com.linjw.myoa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class TopicAction extends ModelDrivenBaseAction<Topic>{
	private Long forumId;

	
	/**
	 * 显示单个主题（主帖+回帖列表）
	 */
	public String show(){
		//准备数据　：　topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic",topic);
		/*//准备数据：replyList
		List<Reply> replyList = replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replyList);*/
	/*	//准备分页信息　v1
		PageBean pageBean = replyService.getPageBeanByTopic(pageNum,pageSize,topic);
		ActionContext.getContext().getValueStack().push(pageBean);*/
	/*	//准备分布信息　v2
		String hql = "from Reply r where r.topic=? order by r.postTime asc";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(topic);
		
		PageBean pageBean = replyService.getPageBean(pageNum,pageSize,hql,parameters);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		
		//准备分页　---　最终版
		new QueryHelper(Reply.class,"r")//
		   .addCondition("r.topic=?",topic)//
		   .addOrderProperty("r.postTime",true)//
		   .preparePageBean(replyService, pageNum, pageSize);
		
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
	/**
	 * 删除主帖子
	 * @return
	 */
	public String delete()throws Exception{
	
	//	topicService.delete(model.getId());
		Topic topic = topicService.getById(model.getId());
		topicService.delete(topic);
		return "delete";
	} 
	/**
	 *修改主题帖子
	 */
	public String editUI()throws Exception{
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(topic);
		return "editUI";
	}
	/**
	 * 修改
	 */
	public String edit() throws Exception{
		Topic topic = topicService.getById(model.getId());
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		topicService.update(topic);
		return "toShow";
	}
	/**
	 * 设置精华帖子
	 * @return
	 * @throws Exception
	 */
	
	public String best()throws Exception{
		Topic topic = new Topic();
		Topic topic_best = topicService.getById(model.getId());
		topic_best.setType(topic.TYPE_BEST);
		topicService.update(topic_best); 
		return "toShow";
	}
	/**
	 * 设置置顶帖子
	 * @return
	 */
	public String top()throws Exception{
		Topic topic = new Topic();
		Topic topic_best = topicService.getById(model.getId());
		topic_best.setType(topic.TYPE_TOP);
		topicService.update(topic_best); 
		return "toShow";
	}
	/**
	 * 设置普通帖子
	 * @return
	 */
	public String normal()throws Exception{
		Topic topic = new Topic();
		Topic topic_best = topicService.getById(model.getId());
		topic_best.setType(topic.TYPE_NORMAL);
		topicService.update(topic_best); 
		return "toShow";
	}
	/**
	 * 移动到其它板块
	 * @return
	 */
	public String moveUI() throws Exception{
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(topic);
		
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList",forumList);
		System.out.println("---------------------------进入UI界面");
		return "moveUI";
	}
	
	/**
	 * 移动
	 * @return
	 */
	public String move() throws Exception{
		System.out.println("------------------------------打印id:" );
		System.out.println("-------------------------------打印：forumId");
		Topic topic = topicService.getById(model.getId());
		topic.setForum(forumService.getById(forumId)); 
		topicService.update(topic);
		return "toShow";
	}
	//--- 
public Long getForumId() {
	return forumId;
}
public void setForumId(Long forumId) {
	this.forumId = forumId;
}
}
