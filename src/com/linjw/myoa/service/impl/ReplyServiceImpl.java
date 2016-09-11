package com.linjw.myoa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.linjw.myoa.base.DaoSupportImpl;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.model.Reply;
import com.linjw.myoa.model.Topic;
import com.linjw.myoa.service.ReplyService;
@Service
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService{

	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				"from Reply r where r.topic=? order by r.postTime asc")
				.setParameter(0,topic)
				.list();
	}

	@Override
	public void save(Reply reply) {
		//1.保存
		getSession().save(reply);
		
		//2.维护相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount()+1);// 文章数量（主题数+回复数）
		topic.setReplyCount(topic.getReplyCount()+1);// 回复数量
		topic.setLastReply(reply); // 最后发表的回复
		topic.setLastUpdateTime(reply.getPostTime());// 最后更新时间（主题的发表时间或最后回复的时间）
		
		getSession().update(topic);
		getSession().update(forum);
	}

}
