package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.PageBean;
import com.linjw.myoa.model.Reply;
import com.linjw.myoa.model.Topic;

public interface ReplyService extends DaoSupport<Reply>{

	public List<Reply> findByTopic(Topic topic);
	/**
	 * 分页显示回复帖子
	 * @param pageNum
	 * @param pageSize
	 * @param topic
	 * @return
	 */
	public PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic) ;

}
