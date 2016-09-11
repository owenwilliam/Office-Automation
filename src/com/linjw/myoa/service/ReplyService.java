package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.Reply;
import com.linjw.myoa.model.Topic;

public interface ReplyService extends DaoSupport<Reply>{

	public List<Reply> findByTopic(Topic topic);

}
