package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.model.Topic;

public interface TopicService extends DaoSupport<Topic>{
	public List<Topic> findByForum(Forum forum);

}
